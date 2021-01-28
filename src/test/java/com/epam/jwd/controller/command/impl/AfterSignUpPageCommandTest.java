package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.CommandType;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.ResponseContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

public class AfterSignUpPageCommandTest {

    @Mock
    HttpServletRequest req;
    HttpSession session;
    Command command;

    @Before
    public void setUp() throws Exception {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        req = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        command = CommandFactory.getCommand(CommandType.AFTER_SIGNUP_PAGE.getUrl());
    }

    @Test
    public void testExecute() {
        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.AFTER_SIGNUP_PAGE);
    }
}