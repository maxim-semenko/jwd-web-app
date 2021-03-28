package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.EntityResultSet;
import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.UnknownEnumFacultyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that get {@link ResultSet} from SQL
 * and create {@link Faculty} by params.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class FacultyResultSet implements EntityResultSet<Faculty> {

    private static FacultyResultSet instance;

    public static FacultyResultSet getInstance() {
        if (instance == null) {
            instance = new FacultyResultSet();
        }
        return instance;
    }

    /**
     * Method that returns {@link Faculty} by params from {@link ResultSet}.
     *
     * @param resultSet {@link ResultSet}
     * @return {@link Faculty}
     * @throws SQLException                exception
     * @throws UnknownEnumFacultyException exception
     */
    @Override
    public Faculty execute(ResultSet resultSet) throws SQLException, UnknownEnumFacultyException {
        return new Faculty(EnumFaculty.resolveById(resultSet.getInt(1)), resultSet.getInt(3));
    }
}
