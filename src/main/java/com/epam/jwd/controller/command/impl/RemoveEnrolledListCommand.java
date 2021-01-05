package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpSession;

public class RemoveEnrolledListCommand implements Command {

    private static final ResponseContext ADMIN_CABINET_REDIRECT = () -> PathToPages.ADMIN_CABINET_REDIRECT;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        if (session.getAttribute("User") != null) {
            try {
                UserService.getInstance().removeEnrolledList();
                AppContext.isEnrolledList = false;
                requestContext.setAttribute("enrolledList", false);
                session.setAttribute("EnrolledList", false);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
        return ADMIN_CABINET_REDIRECT;

    }
}
