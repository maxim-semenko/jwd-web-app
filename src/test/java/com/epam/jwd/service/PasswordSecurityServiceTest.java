package com.epam.jwd.service;

import org.junit.Assert;
import org.junit.Test;

public class PasswordSecurityServiceTest {

    PasswordSecurityService service = PasswordSecurityService.getInstance();

    @Test
    public void testGetInstance() {
        Assert.assertEquals(service, PasswordSecurityService.getInstance());
    }

    @Test
    public void testDoHashing() {
        String password = service.doHashing("12345678");
        Assert.assertEquals(password, "25D55AD283AA400AF464C76D713C07AD");
    }
}