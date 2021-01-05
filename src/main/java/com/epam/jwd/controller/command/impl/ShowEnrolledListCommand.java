package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowEnrolledListCommand implements Command {

    private static final ResponseContext SHOW_ENROLLED_LIST_PAGE = () -> PathToPages.SHOW_ENROLLED_LIST_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("showEnrolledList", UserService.getInstance().getEnrolledList());
        return SHOW_ENROLLED_LIST_PAGE;
    }
}
