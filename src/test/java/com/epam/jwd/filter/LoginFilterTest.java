package com.epam.jwd.filter;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.config.AdminConfiguration;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginFilterTest {

    @Mock
    static ServletRequest servletRequest;
    static ServletResponse servletResponse;
    static FilterChain filterChain;

    static HttpServletRequest req;
    static HttpServletResponse resp;
    static RequestDispatcher requestDispatcher;
    static HttpSession session;

    @BeforeClass
    public static void beforeClass() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();

        servletRequest = mock(ServletRequest.class);
        servletResponse = mock(ServletResponse.class);
        filterChain = mock(FilterChain.class);

        req = (mock(HttpServletRequest.class));
        resp = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);

        session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
    }

    @Test
    public void testDoFilterWhenUser() throws IOException, ServletException {
        when(req.getParameter("signIn_login")).thenReturn("test");
        when(req.getParameter("signIn_password")).thenReturn("test1111");
        new LoginFilter().doFilter(req, resp, filterChain);
    }

    @Test
    public void testDoFilterWhenAdmin() throws IOException, ServletException {
        when(req.getParameter("signIn_login")).thenReturn(AdminConfiguration.getInstance().getLogin());
        when(req.getParameter("signIn_password")).thenReturn(AdminConfiguration.getInstance().getPassword());
        new LoginFilter().doFilter(req, resp, filterChain);
    }

    @Test
    public void testDoFilterWhenNotFound() throws IOException, ServletException {
        when(session.getAttribute("notFound")).thenReturn(true);
        when(session.getAttribute("isRedirectHome")).thenReturn(null);
        when(req.getParameter("signIn_login")).thenReturn("maksim2222");
        when(req.getParameter("signIn_password")).thenReturn("12345678");
        new LoginFilter().doFilter(req, resp, filterChain);
    }
}