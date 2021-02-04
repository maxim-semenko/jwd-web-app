package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.EntityResultSet;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.EnumUserStatus;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserResultSet implements EntityResultSet<User> {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private static UserResultSet instance;

    public static UserResultSet getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new UserResultSet();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * @param resultSet {@link ResultSet}
     * @return {@link User}
     * @throws SQLException exception
     */
    @Override
    public User execute(ResultSet resultSet) throws SQLException {
        User temp = new User(new UserBuilder()
                .setLogin(resultSet.getString(2))
                .setPassword(resultSet.getString(3))
                .setFirstname(resultSet.getString(4))
                .setLastname(resultSet.getString(5))
                .setEmail(resultSet.getString(6))
                .setUserRole(EnumUserRole.resolveById(resultSet.getInt(7)))
                .setUserStatus(EnumUserStatus.resolveById(resultSet.getInt(8)))
                .setFacultyId(resultSet.getInt(9))
                .setAverageScore(resultSet.getInt(10))
                .setRussianExamScore(resultSet.getInt(11))
                .setMathExamScore(resultSet.getInt(12))
                .setPhysicsExamScore(resultSet.getInt(13)));
        temp.setId(resultSet.getInt(1));
        return temp;
    }
}
