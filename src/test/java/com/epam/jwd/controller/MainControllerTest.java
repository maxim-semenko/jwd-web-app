package com.epam.jwd.controller;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.controller.command.impl.CustomRequestContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainControllerTest {

    @Mock
    static HttpServletRequest req;
    static HttpServletResponse resp;
    static RequestDispatcher requestDispatcher;
    static HttpSession session;



    @BeforeClass
    public static void beforeClass() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        when(req.getParameter("command")).thenReturn("home");
        final Command command = CommandFactory.getCommand(req.getParameter("command"));

        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        when(req.getRequestDispatcher(responseContext.getPage())).thenReturn(requestDispatcher);

        new MainController().doGet(req, resp);
        Assert.assertEquals(responseContext.getResponseType(), ResponseContext.ResponseType.FORWARD);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("command")).thenReturn("sign-out");
        final Command command = CommandFactory.getCommand(req.getParameter("command"));

        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        when(req.getRequestDispatcher(responseContext.getPage())).thenReturn(requestDispatcher);

        new MainController().doPost(req, resp);
        Assert.assertEquals(responseContext.getResponseType(), ResponseContext.ResponseType.REDIRECT);
    }

}