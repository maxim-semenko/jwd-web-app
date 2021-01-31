package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.service.UserService;

/**
 * Class command that remove {@link User} by admin.
 *
 * @version 0.0.1
 */

public class RemoveUserByAdminCommand implements Command {

    public static final ResponseContext SHOW_ALL_USERS_PAGE_REDIRECT
            = new ResponseContextImpl(PathToPages.SHOW_ALL_USERS_PAGE_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    private static final ResponseContext FIND_USERS_BY_CRITERIA_REDIRECT
            = new ResponseContextImpl(PathToPages.FIND_USERS_BY_CRITERIA_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService.getInstance().removeById(Integer.parseInt(requestContext.getParamMap().get("id")));
        requestContext.setAttribute("showAllUsers", UserService.getInstance().selectAll());
        if (requestContext.getHttpSession().getAttribute("adminPage") == "allUsers") {
            return SHOW_ALL_USERS_PAGE_REDIRECT;
        } else {
            return FIND_USERS_BY_CRITERIA_REDIRECT;
        }
    }
}
