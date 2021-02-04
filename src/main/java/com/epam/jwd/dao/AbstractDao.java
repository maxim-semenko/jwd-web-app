package com.epam.jwd.dao;

import com.epam.jwd.exception.UnknownMethodException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.util.List;

/**
 * {@link AbstractDao} interface that contain basic methods
 * for working whit database and one default method that
 * returns {@link Connection} from {@link ConnectionPool}.
 *
 * @param <T> any POJO class
 */

public interface AbstractDao<T> {

    List<T> selectAll();

    T selectById(Integer id);

    boolean insert(T t);

    void update(T t) throws UnknownMethodException;

    boolean removeById(Integer id) throws UnknownMethodException;

    int getMaxId() throws UnknownMethodException;

    default Connection getConnection() {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
