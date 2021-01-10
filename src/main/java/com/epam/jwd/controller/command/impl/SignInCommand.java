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

    private static final ResponseContext USER_CABINET_REDIRECT = () -> PathToPages.USER_CABINET_REDIRECT;
    private static final ResponseContext HOME_REDIRECT = () -> PathToPages.HOME_REDIRECT;
    private static final ResponseContext ADMIN_CABINET_REDIRECT = () -> PathToPages.ADMIN_CABINET_REDIRECT;


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        if ((boolean) session.getAttribute("notFound")) {
            return HOME_REDIRECT;
        }
        User user = (User) session.getAttribute("user");
        if (user.getLogin().equals(AdminConfiguration.getInstance().getLogin())
                && user.getPassword().equals(AdminConfiguration.getInstance().getPassword())) {
            return ADMIN_CABINET_REDIRECT;
        }
        return USER_CABINET_REDIRECT;
    }
}
