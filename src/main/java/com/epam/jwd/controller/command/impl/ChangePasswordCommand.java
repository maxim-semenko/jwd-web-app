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

    private static final ResponseContext HOME_REDIRECT = () -> PathToPages.HOME_REDIRECT;
    private static final ResponseContext NEW_PASSWORD_PAGE =()-> PathToPages.NEW_PASSWORD_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();

        User user = (User) session.getAttribute("UserForRestorePassword");
        Integer code = (Integer) session.getAttribute("CheckCode");

        Integer inputCode = Integer.parseInt(requestContext.getParamList().get(1));
        String inputPassword = requestContext.getParamList().get(2);

        if (inputCode.equals(code)) {
            user.setPassword(PasswordSecurityService.getInstance().doHashing(inputPassword));
            try {
                UserService.getInstance().update(user);
                session.invalidate();
            } catch (ValidatorException e) {
                log.error("Can't update user " + e);
            }
        } else {
            requestContext.setAttribute("ErrorCode", true);
            return NEW_PASSWORD_PAGE;
        }

        return HOME_REDIRECT;
    }
}
