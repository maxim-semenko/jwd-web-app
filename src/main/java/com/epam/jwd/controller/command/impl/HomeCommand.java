package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

import javax.servlet.http.HttpSession;

public final class HomeCommand implements Command {

    private static final ResponseContext HOME_PAGE = () -> PathToPages.HOME_PAGE;
    private static final ResponseContext USER_CABINET_REDIRECT = () -> PathToPages.USER_CABINET_REDIRECT;


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession httpSession = requestContext.getHttpSession();
        if (httpSession.getAttribute("User") != null) {
            return USER_CABINET_REDIRECT;
        }
        return HOME_PAGE;
    }
}
