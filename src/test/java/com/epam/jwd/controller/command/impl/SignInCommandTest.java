package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.context.config.AdminConfiguration;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SignInCommandTest {

    @Mock
    HttpServletRequest req;
    HttpSession session;
    Command command;

    @Before
    public void setUp() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        req = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        command = CommandFactory.getCommand("sign-in");
    }

    @Test
    public void testExecuteThatReturnAdminCabinetRedirect() {
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(AdminConfiguration.getInstance().getAdmin());
        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.ADMIN_CABINET_REDIRECT);
    }

    @Test
    public void testExecuteThatReturnUserCabinetRedirect() {
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(new UserBuilder()
                .setLogin("Test1111")
                .setPassword("Test1111")));

        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.USER_CABINET_REDIRECT);
    }

}