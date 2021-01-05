package com.epam.jwd.validator;

import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {

    @Test
    public void testGetInstance() {
        UserValidator userValidator = UserValidator.getInstance();
        Assert.assertEquals(userValidator, UserValidator.getInstance());
    }

    @Test
    public void testValidate() {
        User user = new User(new UserBuilder()
                .setLogin("GDtf4GA54")
                .setPassword("221123522")
                .setEmail("mak23523ss@gmail.com")
                .setFirstname("Dima")
                .setLastname("Semenko")
                .setAverageScore(100)
                .setRussianExamScore(100)
                .setMathExamScore(100)
                .setPhysicsExamScore(100)
                .setFacultyId(1)
                .setUserRole(EnumUserRole.CLIENT));
        Assert.assertTrue(UserValidator.getInstance().validate(user));
    }
}