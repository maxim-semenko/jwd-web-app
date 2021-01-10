package com.epam.jwd.entity;

import org.junit.Assert;
import org.junit.Test;

public class EnumUserStatusTest {


    @Test
    public void resolveByIdUnknown() {
        EnumUserStatus status = EnumUserStatus.resolveById(1);
        Assert.assertEquals(status, EnumUserStatus.UNKNOWN);
    }

    @Test
    public void resolveByIdEnrolled() {
        EnumUserStatus status = EnumUserStatus.resolveById(2);
        Assert.assertEquals(status, EnumUserStatus.ENROLLED);
    }

    @Test
    public void resolveByIdNoEnrolled() {
        EnumUserStatus status = EnumUserStatus.resolveById(3);
        Assert.assertEquals(status, EnumUserStatus.NO_ENROLLED);
    }

    @Test
    public void resolveByIdNull() {
        EnumUserStatus status = EnumUserStatus.resolveById(4);
        Assert.assertNull(status);
    }

    @Test
    public void getId() {
        EnumUserStatus status = EnumUserStatus.UNKNOWN;
        Assert.assertEquals(status.getId(), 1);
    }
}