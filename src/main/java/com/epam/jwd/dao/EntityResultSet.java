package com.epam.jwd.dao;

import com.epam.jwd.exception.UnknownEnumFacultyException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link EntityResultSet} interface that has one method
 * that gets {@link ResultSet} from database, parse it and return {@link T}.
 *
 * @param <T> any POJO class
 * @version 0.0.1
 */

public interface EntityResultSet<T> {

    T execute(ResultSet resultSet) throws SQLException, UnknownEnumFacultyException;

}
