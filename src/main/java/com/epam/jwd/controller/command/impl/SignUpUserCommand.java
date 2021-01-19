package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

/**
 * Class command that signUp {@link User}.
 *
 * @version 0.0.1
 */

@Log4j2
public final class SignUpUserCommand implements Command {

    private static final ResponseContext SIGNUP_PAGE
            = new ResponseContextImpl(PathToPages.SIGNUP_PAGE, ResponseContext.ResponseType.FORWARD);

    private static final ResponseContext AFTER_SIGNUP_REDIRECT
            = new ResponseContextImpl(PathToPages.AFTER_SIGNUP_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    /**
     * Method receives {@link RequestContext} requestContext and checks
     * if there is such {@link User} a user. If not, then it tries
     * to create a new user and insert it into the database.
     *
     * @param requestContext {@link RequestContext} params
     * @return {@link ResponseContext} page
     */
    @Override
    public ResponseContext execute(final RequestContext requestContext) {
        requestContext.setAttribute("checkExistUser", false);
        requestContext.setAttribute("checkError", false);

        final int countParam = 11;
        UserService userService = UserService.getInstance();

        // Needed params to create user
        if (requestContext.getParamMap().size() == countParam) {
            User user = userService.createByParams(requestContext.getParamMap());
            Optional<User> optionalUser = userService.getByCriteria(UserCriteria.builder()
                    .login(user.getLogin())
                    .build());
            if (optionalUser.isPresent()) {
                requestContext.setAttribute("checkExistUser", true);
            } else {
                try {
                    userService.insert(user);
                    return AFTER_SIGNUP_REDIRECT;
                } catch (ValidatorException e) {
                    requestContext.setAttribute("checkError", true);
                    log.error("Can't register user " + e);
                }
            }
        }
        return SIGNUP_PAGE;
    }
}