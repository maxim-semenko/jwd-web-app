package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.UserService;

public class RemoveUserByAdminCommand implements Command {

    public static final ResponseContext ALL_USERS_PAGE = () -> PathToPages.SHOW_ALL_USERS_PAGE;


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService.getInstance().removeById(Integer.parseInt(requestContext.getParamMap().get("id")));
        requestContext.setAttribute("showAllUsers", UserService.getInstance().selectAll());
        return ALL_USERS_PAGE;
    }
}
