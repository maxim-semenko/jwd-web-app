package com.epam.jwd.filter;

import com.epam.jwd.context.config.AdminConfiguration;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("signIn_login");
        final String password = req.getParameter("signIn_password");

        final HttpSession session = req.getSession();
        session.setMaxInactiveInterval(1800);

        if (session.getAttribute("User") == null && login != null && password != null) {
            if (login.equals(AdminConfiguration.getInstance().getLogin())
                    && password.equals(AdminConfiguration.getInstance().getPassword())) {
                session.setAttribute("User", AdminConfiguration.getInstance().getAdmin());
            } else {
                Optional<User> optionalUser = UserService
                        .getInstance()
                        .getByCriteria(UserCriteria
                                .builder()
                                .login(login)
                                .password(password)
                                .build());
                optionalUser.ifPresent(user -> session.setAttribute("User", user));
            }
            session.setAttribute("notFound", false);
            if (session.getAttribute("User") == null) {
                log.info("User is not login");
                session.setAttribute("notFound", true);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
