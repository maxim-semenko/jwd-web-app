package com.epam.jwd.validator;

import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {

    @Test
    public void testValidate() {
        String input = "maks.semenko@gmail.com";
        Assert.assertTrue(EmailValidator.getInstance().validate(input));
    }
}