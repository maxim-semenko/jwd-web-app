package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

import javax.servlet.annotation.WebServlet;

public class UserEditPageCommand implements Command {

    private static final ResponseContext USER_EDIT_PAGE =
            new ResponseContextImpl(PathToPages.USER_EDIT_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return USER_EDIT_PAGE;
    }
}
