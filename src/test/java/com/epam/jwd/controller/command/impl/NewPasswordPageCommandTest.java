package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewPasswordPageCommandTest {

    @Mock
    HttpServletRequest req;
    Command command;

    @Before
    public void setUp() {
        req = mock(HttpServletRequest.class);
        command = CommandFactory.getCommand("new-password");
    }

    @Test
    public void testExecute() {
        when(req.getParameter("11")).thenReturn("false");

        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.NEW_PASSWORD_PAGE);
    }
}