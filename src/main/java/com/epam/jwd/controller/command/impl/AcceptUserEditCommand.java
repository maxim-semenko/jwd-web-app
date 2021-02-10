package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.PasswordSecurityService;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Class command that get accept and
 * edit {@link User} user data.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Log4j2
public class AcceptUserEditCommand implements Command {

    private static final ResponseContext USER_EDIT_PAGE_REDIRECT
            = new ResponseContextImpl(PathToPages.USER_EDIT_PAGE_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private User newUser;
    private User oldUser;
    private final UserService userService = UserService.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        if (session.getAttribute("user") != null) {
            oldUser = (User) requestContext.getHttpSession().getAttribute("user");
            getNewUser(requestContext);

            if (checkExistLogin(newUser.getLogin(), oldUser.getLogin()).isPresent()) {
                requestContext.setAttribute("checkExistUser", true);
            }
            checkPassword(requestContext);

            try {
                userService.update(newUser);
                session.setAttribute("user", newUser);
            } catch (ValidatorException e) {
                log.error("Can't update user" + e);
            }
        }
        return USER_EDIT_PAGE_REDIRECT;
    }

    private void getNewUser(RequestContext requestContext) {
        newUser = userService.createByParams(requestContext.getParamMap());
        newUser.setUserStatus(oldUser.getUserStatus());
        newUser.setUserRole(oldUser.getUserRole());
        newUser.setId(oldUser.getId());
    }

    private Optional<User> checkExistLogin(String newLogin, String oldLogin) {
        Optional<User> optional = Optional.empty();
        if (!newLogin.equals(oldLogin)) {
            UserCriteria userCriteria = UserCriteria
                    .builder()
                    .login(newLogin)
                    .build();
            optional = userService.getByCriteria(userCriteria);
        }
        return optional;
    }

    private void checkPassword(RequestContext requestContext) {
        if (!requestContext.getParamList().get(4).equals(oldUser.getPassword())) {
            newUser.setPassword(PasswordSecurityService.
                    getInstance().
                    doHashing(requestContext.getParamList().get(4)));
        }
    }
}
