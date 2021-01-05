package com.epam.jwd.dao.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.DataBasePropertiesReaderUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void testGetInstance() {
        AppContext.getInstance().initProperties();
        UserDao userDao = UserDao.getInstance();
        Assert.assertEquals(userDao, UserDao.getInstance());
    }

    @Test
    public void testSelectAll() {
        AppContext.getInstance().init();
        List<User> userList = UserDao.getInstance().selectAll();
    }

    @Test
    public void testSelectById() {
        AppContext.getInstance().init();
        UserDao.getInstance().selectById(1);
    }

    @Test
    public void testInsert() throws ValidatorException {
        AppContext.getInstance().init();
        User.COUNT_ID = UserService.getInstance().getMaxId();
        User user = new User(new UserBuilder()
                .setLogin("test11")
                .setPassword("test")
                .setEmail("test")
                .setFirstname("test")
                .setLastname("test")
                .setAverageScore(80)
                .setRussianExamScore(10)
                .setMathExamScore(0)
                .setPhysicsExamScore(83)
                .setUserRole(EnumUserRole.CLIENT)
                .setFacultyId(3));

        UserService.getInstance().insert(user);

    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testRemoveById() {
        AppContext.getInstance().init();

        Assert.assertTrue(UserDao.getInstance().removeById(29));
    }

    @Test
    public void testCountId() {
        AppContext.getInstance().init();
        //Assert.assertEquals(UserDao.getInstance().getMaxId(), 20);
    }

    @Test
    public void testInsertToEnrolledList() {
    }

    @Test
    public void testInsertAdmin() {
    }
}