package com.epam.jwd.validator;

import org.junit.Assert;
import org.junit.Test;

public class NameValidatorTest {

    @Test
    public void testGetInstance() {
        NameValidator nameValidator = NameValidator.getInstance();
        Assert.assertEquals(nameValidator, NameValidator.getInstance());
    }

    @Test
    public void testValidate() {
        String input = "Максим";
        Assert.assertTrue(NameValidator.getInstance().validate(input));
    }
}