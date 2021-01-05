package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;

@Log4j2
public class SignOutCommand implements Command {

    private static final ResponseContext HOME_REDIRECT = () -> PathToPages.HOME_REDIRECT;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        log.info("User sign out from system");
        HttpSession session = requestContext.getHttpSession();
        session.invalidate();
        return HOME_REDIRECT;
    }
}
