package com.epam.jwd.dao.impl;

import com.epam.jwd.context.config.DataBaseConfiguration;
import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.entity.User;
import com.epam.jwd.exception.UnknownMethodException;
import com.epam.jwd.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class UserDao implements AbstractDao<User> {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final UserResultSet userResultSet = UserResultSet.getInstance();
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private static UserDao instance;

    private static final String SQL_SELECT_ALL_USERS = "SELECT app_user.*,\n" +
            "       rf.faculty_id,\n" +
            "       uc.average_score,\n" +
            "       uc.russian_score,\n" +
            "       uc.math_score,\n" +
            "       uc.physics_score\n" +
            "FROM app_user\n" +
            "         JOIN registered_faculty rf on app_user.id = rf.user_id\n" +
            "         JOIN user_certificate uc on app_user.id = uc.user_id";

    private static final String SQL_SELECT_USER_BY_ID = "SELECT app_user.*,\n" +
            "       rf.faculty_id,\n" +
            "       uc.average_score,\n" +
            "       uc.russian_score,\n" +
            "       uc.math_score,\n" +
            "       uc.physics_score\n" +
            "FROM app_user\n" +
            "         JOIN registered_faculty rf on app_user.id = rf.user_id\n" +
            "         JOIN user_certificate uc on app_user.id = uc.user_id\n" +
            "WHERE id = ?";

    private static final String SQL_SELECT_MAX_ID = "SELECT MAX(id) FROM app_user";

    private static final String SQL_INSERT_USER =
            "INSERT INTO app_user (id, login, password, firstname, lastname, email, role_id, status_id) VALUES (?,?,?,?,?,?,?,?)";

    private static final String SQL_INSERT_USER_CERTIFICATE
            = "INSERT INTO user_certificate(average_score, russian_score, math_score, physics_score, user_id) VALUES (?,?,?,?,?)";

    private static final String SQL_INSERT_USER_FACULTY =
            "INSERT INTO registered_faculty(user_id, faculty_id) VALUES (?,?)";

    private static final String SQL_INSERT_USER_ENROLLED = "INSERT INTO user_enrolled (user_id, sum_score) VALUES (?,?)";

    private static final String SQL_DELETE_ALL_USERS = "DELETE FROM app_user";

    private static final String SQL_DELETE_ALL_USERS_CERTIFICATE = "DELETE FROM user_certificate";

    private static final String SQL_DELETE_ALL_USERS_FACULTY = "DELETE FROM registered_faculty";

    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM app_user WHERE id = ?";

    private static final String SQL_DELETE_USER_CERTIFICATE_BY_ID = "DELETE FROM user_certificate WHERE user_id = ?";

    private static final String SQL_DELETE_USER_FACULTY_BY_ID = "DELETE FROM registered_faculty WHERE user_id = ?";

    private static final String SQL_UPDATE_USER = "UPDATE app_user\n" +
            "SET login     = ?,\n" +
            "    password  = ?,\n" +
            "    firstname = ?,\n" +
            "    lastname  = ?,\n" +
            "    email     = ?,\n" +
            "    status_id = ?\n" +
            "WHERE id = ?";

    private static final String SQL_UPDATE_USER_CERTIFICATE = "UPDATE user_certificate\n" +
            "SET average_score = ?,\n" +
            "    russian_score = ?,\n" +
            "    math_score    = ?,\n" +
            "    physics_score = ?\n" +
            "WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_FACULTY = "UPDATE registered_faculty\n" +
            "SET faculty_id = ?\n" +
            "WHERE user_id = ?";

    private static final String SQL_COUNT_USER_ENROLLED = "SELECT COUNT(*) FROM user_enrolled";

    private static final String SQL_DELETE_ALL_USER_ENROLLED = "DELETE FROM user_enrolled";

    private static final String SQL_SELECT_ID_USER_ENROLLED = "SELECT * FROM user_enrolled";

    public static UserDao getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new UserDao();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * @return
     */
    @Override
    public List<User> selectAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(userResultSet.execute(resultSet));
            }
            log.info("Select all from app_user");
        } catch (SQLException e) {
            log.info("Error to select all from app_user" + e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }

    @Override
    public User selectById(Integer id) {
        User user = null;
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = userResultSet.execute(resultSet);
            }
            log.info("Select user from app_user");
        } catch (SQLException e) {
            log.error("Error to select user from app_user " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return user;
    }

    @Override
    public boolean insert(User user) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT_USER);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_INSERT_USER_CERTIFICATE);
             PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_INSERT_USER_FACULTY);
        ) {
            connection.setAutoCommit(false);

            preparedStatement1.setInt(1, user.getId());
            preparedStatement1.setString(2, user.getLogin());
            preparedStatement1.setString(3, user.getPassword());
            preparedStatement1.setString(4, user.getFirstname());
            preparedStatement1.setString(5, user.getLastname());
            preparedStatement1.setString(6, user.getEmail());
            preparedStatement1.setInt(7, user.getUserRole().getId());
            preparedStatement1.setInt(8, user.getUserStatus().getId());
            preparedStatement1.executeUpdate();

            preparedStatement2.setInt(1, user.getAverageScore());
            preparedStatement2.setInt(2, user.getRussianExamScore());
            preparedStatement2.setInt(3, user.getMathExamScore());
            preparedStatement2.setInt(4, user.getPhysicsExamScore());
            preparedStatement2.setInt(5, user.getId());
            preparedStatement2.executeUpdate();

            preparedStatement3.setInt(1, user.getId());
            preparedStatement3.setInt(2, user.getFacultyId());
            preparedStatement3.executeUpdate();

            log.info("User are inserted into database");
            connection.commit();
        } catch (SQLException e) {
            log.error("Can't add user" + e);
            try {
                connection.rollback();
                log.error("Connection rollback");
            } catch (SQLException ex) {
                log.error("Can't rollback connection " + ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
                log.info("Connection are returned to pool");
            } catch (SQLException e) {
                log.error("Can't release a connection " + e);
            }
        }
        return true;
    }

    @Override
    public void update(User user) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_UPDATE_USER);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_UPDATE_USER_CERTIFICATE);
             PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_UPDATE_USER_FACULTY);
        ) {
            connection.setAutoCommit(false);

            preparedStatement1.setString(1, user.getLogin());
            preparedStatement1.setString(2, user.getPassword());
            preparedStatement1.setString(3, user.getFirstname());
            preparedStatement1.setString(4, user.getLastname());
            preparedStatement1.setString(5, user.getEmail());
            preparedStatement1.setInt(6, user.getUserStatus().getId());
            preparedStatement1.setInt(7, user.getId());
            preparedStatement1.executeUpdate();

            preparedStatement2.setInt(1, user.getAverageScore());
            preparedStatement2.setInt(2, user.getRussianExamScore());
            preparedStatement2.setInt(3, user.getMathExamScore());
            preparedStatement2.setInt(4, user.getPhysicsExamScore());
            preparedStatement2.setInt(5, user.getId());
            preparedStatement2.executeUpdate();

            preparedStatement3.setInt(1, user.getFacultyId());
            preparedStatement3.setInt(2, user.getId());
            preparedStatement3.executeUpdate();

            log.info("User are updated into database");
            connection.commit();
        } catch (SQLException e) {
            log.error("Can't update user" + e);
            try {
                connection.rollback();
                log.error("Connection rollback");
            } catch (SQLException ex) {
                log.error("Can't rollback connection " + ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
                log.info("Connection are returned to pool");
            } catch (SQLException e) {
                log.error("Can't release a connection " + e);
            }
        }
    }

    @Override
    public void remove(User user) throws UnknownMethodException {
        throw new UnknownMethodException();
    }

    @Override
    public boolean removeById(Integer id) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_DELETE_USER_CERTIFICATE_BY_ID);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_DELETE_USER_FACULTY_BY_ID);
             PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
        ) {
            connection.setAutoCommit(false);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
            preparedStatement3.setInt(1, id);
            preparedStatement3.executeUpdate();
            log.info("User are removed from app_user");
            connection.commit();
        } catch (SQLException e) {
            log.error("Can't remove user" + e);
            try {
                connection.rollback();
                log.info("Connection rollback");
            } catch (SQLException ex) {
                log.error("Can't rollback connection " + ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
                log.info("Connection are returned to pool");
            } catch (SQLException e) {
                log.error("Can't release a connection " + e);
            }
        }
        return true;
    }

    public void removeAllUsers() {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_DELETE_ALL_USERS_CERTIFICATE);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_DELETE_ALL_USERS_FACULTY);
             PreparedStatement preparedStatement3 = connection.prepareStatement(SQL_DELETE_ALL_USERS);
        ) {
            connection.setAutoCommit(false);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            log.info("User are removed from app_user");
            connection.commit();
        } catch (SQLException e) {
            log.error("Can't remove user" + e);
            try {
                connection.rollback();
                log.info("Connection rollback");
            } catch (SQLException ex) {
                log.error("Can't rollback connection " + ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
                log.info("Connection are returned to pool");
            } catch (SQLException e) {
                log.error("Can't release a connection " + e);
            }
        }
    }

    @Override
    public int getMaxId() {
        Connection connection = getConnection();
        int id = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MAX_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            log.info("Max id are selected from app_user");
        } catch (SQLException e) {
            log.error("Error to select max id from app_user " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return id;
    }

    public void insertToEnrolledList(User user) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER_ENROLLED)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getSumExams());
            preparedStatement.executeUpdate();
            log.info("User are inserted into user_enrolled");
        } catch (SQLException e) {
            log.error("Error to add user into user_enrolled " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
    }


    public void removeAllFromEnrolledList() {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_USER_ENROLLED)) {
            preparedStatement.executeUpdate();
            log.info("All users are removed from user_enrolled");
        } catch (SQLException e) {
            log.error("Error to remove all users from user_enrolled " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
    }

    public int getCountUserEnrolled() {
        Connection connection = getConnection();
        int count = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_COUNT_USER_ENROLLED)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            log.info("Count from user_enrolled");
        } catch (SQLException e) {
            log.error("Error to count from user_enrolled " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return count;
    }

    public Map<Integer, Integer> selectAllEnrolledList() {
        Map<Integer, Integer> integerMap = new HashMap<>();
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID_USER_ENROLLED)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                integerMap.put(resultSet.getInt(1), resultSet.getInt(2));
            }
            log.info("Select all id from user_enrolled");
        } catch (SQLException e) {
            log.info("Error to select all id from user_enrolled" + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return integerMap;
    }

}
