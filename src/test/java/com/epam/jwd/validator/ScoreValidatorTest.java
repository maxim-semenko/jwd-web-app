package com.epam.jwd.validator;

import org.junit.Assert;
import org.junit.Test;

public class ScoreValidatorTest {

    @Test
    public void testGetInstance() {
       ScoreValidator scoreValidator = ScoreValidator.getInstance();
       Assert.assertEquals(scoreValidator, ScoreValidator.getInstance());
    }

    @Test
    public void testValidate() {
        ScoreValidator scoreValidator = ScoreValidator.getInstance();
        Assert.assertFalse(scoreValidator.validate(102));
    }
}