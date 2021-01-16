package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class ConfirmUserRemoveCommand implements Command {

    private static final ResponseContext CONFIRM_USER_REMOVE_PAGE
            = new ResponseContextImpl(PathToPages.CONFIRM_USER_REMOVE_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return CONFIRM_USER_REMOVE_PAGE;
    }
}