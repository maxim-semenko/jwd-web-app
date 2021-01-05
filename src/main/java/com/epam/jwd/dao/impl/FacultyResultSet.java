package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.EntityResultSet;
import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.UnknownEnumFacultyException;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class FacultyResultSet implements EntityResultSet<Faculty> {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private static FacultyResultSet instance;

    public static FacultyResultSet getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new FacultyResultSet();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    @Override
    public Faculty execute(ResultSet resultSet) throws SQLException, UnknownEnumFacultyException {
        return new Faculty(EnumFaculty.resolveById(resultSet.getInt(1)), resultSet.getInt(3));
    }
}
