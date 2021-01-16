package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class SignUpPageCommand implements Command {

    private static final ResponseContext SIGNUP_PAGE
            = new ResponseContextImpl(PathToPages.SIGNUP_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SIGNUP_PAGE;
    }
}
