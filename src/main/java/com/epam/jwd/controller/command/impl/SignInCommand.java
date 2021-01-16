package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.context.config.AdminConfiguration;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Class command that Sign in {@link User}
 */
public class SignInCommand implements Command {

    private static final ResponseContext USER_CABINET_REDIRECT
            = new ResponseContextImpl(PathToPages.USER_CABINET_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private static final ResponseContext ADMIN_CABINET_REDIRECT
            = new ResponseContextImpl(PathToPages.ADMIN_CABINET_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();

        User user = (User) session.getAttribute("user");
        if (user.getLogin().equals(AdminConfiguration.getInstance().getLogin())
                && user.getPassword().equals(AdminConfiguration.getInstance().getPassword())) {
            return ADMIN_CABINET_REDIRECT;
        }
        return USER_CABINET_REDIRECT;
    }

}
