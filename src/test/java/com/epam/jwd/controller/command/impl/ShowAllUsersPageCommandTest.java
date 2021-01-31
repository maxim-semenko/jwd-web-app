package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAllUsersPageCommandTest {

    @Mock
    HttpServletRequest req;
    RequestContext requestContext;
    HttpSession session;
    Command command;

    @Before
    public void setUp() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        req = mock(HttpServletRequest.class);
        requestContext = mock(RequestContext.class);
        session = mock(HttpSession.class);
        command = CommandFactory.getCommand("admin/all-users");
        when(req.getSession()).thenReturn(session);
    }

    @Test
    public void testExecuteThatReturnShowAllUsersPage() {
        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.SHOW_ALL_USERS_PAGE);
    }
}