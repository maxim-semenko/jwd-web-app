package com.epam.jwd.validator;

import org.junit.Assert;
import org.junit.Test;

public class PasswordValidatorTest {

    @Test
    public void testGetInstance() {
        PasswordValidator nameValidator = PasswordValidator.getInstance();
        Assert.assertEquals(nameValidator, PasswordValidator.getInstance());
    }

    @Test
    public void testValidate() {
        String password = "1B2a345@#$%";
        Assert.assertTrue(PasswordValidator.getInstance().validate(password));
    }
}