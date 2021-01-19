package com.epam.jwd.dao;

import com.epam.jwd.exception.UnknownMethodException;
import com.epam.jwd.pool.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public interface AbstractDao<T> {

    List<T> selectAll();

    T selectById(Integer id);

    boolean insert(T t);

    void update(T t) throws UnknownMethodException;

    void remove(T t) throws UnknownMethodException;

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
