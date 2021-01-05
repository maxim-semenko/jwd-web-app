package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.exception.UnknownMethodException;
import com.epam.jwd.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class UserRoleDao implements AbstractDao<EnumUserRole> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_INSERT_ROLE = "INSERT INTO user_role (name) (?)";

    @Override
    public List<EnumUserRole> selectAll() {
        return null;
    }

    @Override
    public EnumUserRole selectById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(EnumUserRole enumUserRole) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE)) {
            preparedStatement.setString(1, enumUserRole.toString());
            preparedStatement.executeUpdate();
            log.info("UserRole are inserted into database");
        } catch (SQLException e) {
            log.info("Error to add a new userRole " + e);
        } finally {
            connectionPool.releaseConnection(connection);
            log.info("Connection are returned to pool");
        }
        return true;
    }

    @Override
    public void update(EnumUserRole enumUserRole) throws UnknownMethodException {
        throw new UnknownMethodException();
    }

    @Override
    public void remove(EnumUserRole enumUserRole) throws UnknownMethodException {
        throw new UnknownMethodException();
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
