package com.epam.jwd.filter;

import com.epam.jwd.context.PathToPages;
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

/**
 * Login Filter that set {@link User} in {@link HttpSession}.
 *
 * @version 0.0.1
 */

@WebFilter("/*")
@Log4j2
public class LoginFilter implements Filter {

    /**
     * Method that filter {@link User }user signIn.
     *
     * @param servletRequest  {@link HttpServletRequest}
     * @param servletResponse {@link HttpServletResponse}
     * @param filterChain     {@link FilterChain}
     * @throws IOException      exception
     * @throws ServletException exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("signIn_login");
        final String password = req.getParameter("signIn_password");

        final HttpSession session = req.getSession();
        session.setMaxInactiveInterval(1800);

        if (session.getAttribute("user") == null && login != null && password != null) {
            AdminConfiguration adminConfiguration = AdminConfiguration.getInstance();
            if (login.equals(adminConfiguration.getLogin()) && password.equals(adminConfiguration.getPassword())) {
                session.setAttribute("user", adminConfiguration.getAdmin());
            } else {
                setUserInSession(session, login, password);
            }
            checkUserInSession(session);
        }

        if (checkNotFound(session)) {
            session.setAttribute("isRedirectHome", true);
            resp.sendRedirect(PathToPages.HOME_REDIRECT);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    /**
     * Method that checks {@link User} user in {@link HttpSession} session.
     *
     * @param session {@link HttpSession}
     */
    private void checkUserInSession(final HttpSession session) {
        session.setAttribute("notFound", false);
        session.setAttribute("isRedirectHome", null);
        if (session.getAttribute("user") == null) {
            log.info("User is not login");
            session.setAttribute("notFound", true);
        }
    }

    /**
     * Method that sets {@link User} user in {@link HttpSession} session.
     *
     * @param session  {@link HttpSession}
     * @param login    {@link String}
     * @param password {@link String}
     */
    private void setUserInSession(final HttpSession session, final String login, final String password) {
        getOptionalUser(login, password)
                .ifPresent(user -> session.setAttribute("user", user));
    }

    /**
     * Method that try finds {@link User} by
     * {@link String} login and
     * {@link String} password.
     *
     * @param login    {@link String}
     * @param password {@link String}
     * @return {@link Optional<User>}
     */
    private Optional<User> getOptionalUser(final String login, final String password) {
        return UserService
                .getInstance()
                .getByCriteria(UserCriteria
                        .builder()
                        .login(login)
                        .password(password)
                        .build());
    }

    /**
     * Method that checks param notFound in {@link HttpSession}.
     *
     * @param session {@link HttpSession}
     * @return {@link Boolean}
     */
    private Boolean checkNotFound(final HttpSession session) {
        return session.getAttribute("notFound") != null
                && (boolean) session.getAttribute("notFound")
                && session.getAttribute("isRedirectHome") == null;
    }

}
