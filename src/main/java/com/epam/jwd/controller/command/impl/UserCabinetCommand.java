package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

import javax.servlet.http.HttpSession;

public class UserCabinetCommand implements Command {

    private static final ResponseContext USER_CABINET_PAGE =
            new ResponseContextImpl(PathToPages.USER_CABINET_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("enrolledList", AppContext.isEnrolledList);
        return USER_CABINET_PAGE;
    }
}
