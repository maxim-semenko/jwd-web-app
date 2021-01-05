package com.epam.jwd.entity;

import org.junit.Assert;
import org.junit.Test;

public class EnumUserRoleTest {

    EnumUserRole enumUserRole = EnumUserRole.ADMIN;


    @Test
    public void testGetId() {
        Assert.assertEquals(enumUserRole.getId(), 1);
    }

    @Test
    public void testEnumToString() {
        Assert.assertEquals(enumUserRole.toString(), "ADMIN");
    }

    @Test
    public void testResolveById1() {
        Assert.assertEquals(EnumUserRole.resolveById(1), EnumUserRole.ADMIN);
    }

    @Test
    public void testResolveById2() {
        Assert.assertEquals(EnumUserRole.resolveById(2), EnumUserRole.CLIENT);
    }

    @Test
    public void testResolveByIdNull() {
        EnumUserRole enumUserRole = EnumUserRole.resolveById(3);
        Assert.assertNull(enumUserRole);
    }
}