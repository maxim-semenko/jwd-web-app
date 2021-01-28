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
 * @version 0.0.1
 */

@Log4j2
public class AcceptUserEditCommand implements Command {

    private static final ResponseContext USER_CABINET_REDIRECT
            = new ResponseContextImpl(PathToPages.USER_CABINET_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private static final ResponseContext HOME_REDIRECT
            = new ResponseContextImpl(PathToPages.HOME_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private static final ResponseContext USER_EDIT_PAGE
            = new ResponseContextImpl(PathToPages.USER_EDIT_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        UserService userService = UserService.getInstance();
        if (session.getAttribute("user") == null) {
            session.setAttribute("isLogout", true);
            return HOME_REDIRECT;
        }

        User oldUser = (User) session.getAttribute("user");
        User newUser = userService.createByParams(requestContext.getParamMap());
        newUser.setUserStatus(oldUser.getUserStatus());
        newUser.setId(oldUser.getId());

        if (checkExistLogin(newUser.getLogin(), oldUser.getLogin()).isPresent()) {
            requestContext.setAttribute("checkExistUser", true);
            return USER_EDIT_PAGE;
        }

        if (!requestContext.getParamList().get(4).equals(oldUser.getPassword())) {
            newUser.setPassword(PasswordSecurityService.
                    getInstance().
                    doHashing(requestContext.getParamList().get(4)));
        }
        try {
            userService.update(newUser);
            session.setAttribute("user", newUser);
        } catch (ValidatorException e) {
            log.error("Can't update user" + e);
        }
        return USER_CABINET_REDIRECT;
    }

    public Optional<User> checkExistLogin(String newLogin, String oldLogin) {
        Optional<User> optional = Optional.empty();
        if (!newLogin.equals(oldLogin)) {
            UserCriteria userCriteria = UserCriteria
                    .builder()
                    .login(newLogin)
                    .build();
            optional = UserService.getInstance().getByCriteria(userCriteria);
        }
        return optional;
    }
}
