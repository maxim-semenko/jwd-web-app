package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.entity.User;
import com.epam.jwd.exception.UnknownEnumFacultyException;
import com.epam.jwd.exception.UnknownMethodException;
import com.epam.jwd.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link FacultyDao} that sends SQL-requests to database
 * for working with {@link Faculty}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Log4j2
public class FacultyDao implements AbstractDao<Faculty> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final FacultyResultSet facultyResultSet = FacultyResultSet.getInstance();

    private static FacultyDao instance;

    private static final String SQL_INSERT_FACULTY = "INSERT INTO faculty_type (id, name, count_places) VALUES (?,?,?)";

    private static final String SQL_SELECT_ALL_FACULTY = "SELECT * FROM faculty_type";

    private static final String SQL_SELECT_BY_ID_FACULTY = "SELECT * FROM faculty_type WHERE id = ?";

    private static final String SQL_UPDATE_FACULTY = "UPDATE faculty_type SET count_places = ? WHERE id = ?";

    private FacultyDao() {
    }

    public static FacultyDao getInstance() {
        if (instance == null) {
            instance = new FacultyDao();
        }
        return instance;
    }

    /**
     * Method that select all {@link Faculty}.
     *
     * @return {@link List<Faculty>}
     */
    @Override
    public List<Faculty> selectAll() {
        List<Faculty> facultyList = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_FACULTY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                facultyList.add(facultyResultSet.execute(resultSet));
            }
            log.info("Select all from faculty type");
        } catch (SQLException | UnknownEnumFacultyException e) {
            log.error("Error to select all from faculty type" + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return facultyList;
    }

    /**
     * Method that selects {@link Faculty} by id.
     *
     * @param id {@link Integer}
     * @return {@link Faculty}
     */
    @Override
    public Faculty selectById(Integer id) {
        Faculty faculty = null;
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_FACULTY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.info("Faculty are inserted into database");
            while (resultSet.next()) {
                faculty = facultyResultSet.execute(resultSet);
            }
        } catch (SQLException | UnknownEnumFacultyException e) {
            log.error("Error to add a new faculty " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return faculty;
    }

    /**
     * Method that inserts {@link Faculty}
     *
     * @param faculty {@link Faculty}
     * @return {@link Boolean}
     */
    @Override
    public boolean insert(Faculty faculty) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FACULTY)) {
            preparedStatement.setInt(1, faculty.getType().getId());
            preparedStatement.setString(2, faculty.getType().toString());
            preparedStatement.setInt(3, faculty.getCountPlaces());
            preparedStatement.executeUpdate();
            log.info("Faculty are inserted into database");
        } catch (SQLException e) {
            log.error("Error to add a new faculty " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return true;
    }

    /**
     * Method that updates {@link Faculty}.
     *
     * @param faculty {@link Faculty}
     */
    @Override
    public void update(Faculty faculty) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_FACULTY)) {
            preparedStatement.setInt(1, faculty.getCountPlaces());
            preparedStatement.setInt(2, faculty.getType().getId());
            preparedStatement.executeUpdate();
            log.info("Faculty are updated into database");
        } catch (SQLException e) {
            log.error("Error to update a new faculty " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
    }

    @Override
    public boolean removeById(Integer id) throws UnknownMethodException {
        throw new UnknownMethodException();
    }

    @Override
    public int getMaxId() throws UnknownMethodException {
        throw new UnknownMethodException();
    }

}