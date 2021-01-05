package com.epam.jwd.service;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.exception.ValidatorException;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void testGetInstance() {
        UserService userService = UserService.getInstance();
        Assert.assertEquals(userService, UserService.getInstance());
    }

    @Test
    public void testInsert() throws ValidatorException {
        //AppContext.getInstance().init();
        UserService userService = UserService.getInstance();
        User user = new User(new UserBuilder()
                .setLogin("test")
                .setPassword("test1111")
                .setEmail("test@gmail.com")
                .setFirstname("Maxim")
                .setLastname("Semenko")
                .setAverageScore(99)
                .setRussianExamScore(99)
                .setMathExamScore(99)
                .setPhysicsExamScore(83)
                .setUserRole(EnumUserRole.CLIENT)
                .setFacultyId(3));

        //userService.insert(user);
    }

    @Test
    public void testRemoveById() {
        //AppContext.getInstance().init();
        //Assert.assertTrue(UserService.getInstance().removeById(29));
    }

    @Test
    public void testInsertToEnrolledList() {
    }

    @Test
    public void testGetMaxId() {
        //AppContext.getInstance().init();
        //Assert.assertEquals(UserService.getInstance().getMaxId(), 20);
    }


}