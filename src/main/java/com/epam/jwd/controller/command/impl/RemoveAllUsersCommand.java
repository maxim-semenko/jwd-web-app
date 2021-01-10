package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpSession;

public class RemoveAllUsersCommand implements Command {

    private static final ResponseContext ADMIN_CABINET_REDIRECT = () -> PathToPages.ADMIN_CABINET_REDIRECT;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        if (session.getAttribute("user") != null) {
            UserService.getInstance().removeAllUsers();
        }
        return ADMIN_CABINET_REDIRECT;
    }
}
