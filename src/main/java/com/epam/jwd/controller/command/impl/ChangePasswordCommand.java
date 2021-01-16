package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.PasswordSecurityService;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;

@Log4j2
public class ChangePasswordCommand implements Command {

    private static final ResponseContext HOME_REDIRECT
            = new ResponseContextImpl(PathToPages.HOME_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private static final ResponseContext NEW_PASSWORD_PAGE
            = new ResponseContextImpl(PathToPages.NEW_PASSWORD_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();

        User user = (User) session.getAttribute("userForRestorePassword");
        Integer code = (Integer) session.getAttribute("checkCode");

        Integer inputCode = Integer.parseInt(requestContext.getParamMap().get("inputCheckCode"));
        String inputPassword = requestContext.getParamMap().get("newPassword");

        if (inputCode.equals(code)) {
            user.setPassword(PasswordSecurityService.getInstance().doHashing(inputPassword));
            try {
                UserService.getInstance().update(user);
                session.invalidate();
            } catch (ValidatorException e) {
                log.error("Can't update user " + e);
            }
        } else {
            requestContext.setAttribute("errorCode", true);
            return NEW_PASSWORD_PAGE;
        }
        return HOME_REDIRECT;
    }
}
