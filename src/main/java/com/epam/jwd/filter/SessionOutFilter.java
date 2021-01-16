package com.epam.jwd.filter;

import com.epam.jwd.context.PathToPages;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionOutFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final HttpSession session = req.getSession();

        if (session.isNew()) {
            session.setAttribute("isRedirectHome", true);

            System.out.println(session.getAttribute("isLogout"));

                       session.setAttribute("isLogout", true);
           resp.sendRedirect(PathToPages.HOME_REDIRECT);
        } else {
            System.out.println("111111111111");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        System.out.println(session.getAttribute("isLogout"));

    }
}
