package com.epam.jwd.dao;

import com.epam.jwd.exception.UnknownEnumFacultyException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityResultSet<T> {

    T execute(ResultSet resultSet) throws SQLException, UnknownEnumFacultyException;
}
