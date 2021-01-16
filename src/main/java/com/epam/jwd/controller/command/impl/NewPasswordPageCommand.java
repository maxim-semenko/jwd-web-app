package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class NewPasswordPageCommand implements Command {

    private static final ResponseContext NEW_PASSWORD_PAGE
            = new ResponseContextImpl(PathToPages.NEW_PASSWORD_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return NEW_PASSWORD_PAGE;
    }
}
