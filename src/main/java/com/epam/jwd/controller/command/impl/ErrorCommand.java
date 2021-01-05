package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class ErrorCommand implements Command {

    private static final ResponseContext ERROR_PAGE = () -> PathToPages.ERROR_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return ERROR_PAGE;
    }
}
