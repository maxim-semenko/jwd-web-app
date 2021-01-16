package com.epam.jwd.entity;

import org.junit.Assert;
import org.junit.Test;

public class UserBuilderTest {

    @Test
    public void testGetId() {
        UserBuilder userBuilder = new UserBuilder().setId(1);
        Assert.assertEquals(userBuilder.getId(), 1);
    }
}