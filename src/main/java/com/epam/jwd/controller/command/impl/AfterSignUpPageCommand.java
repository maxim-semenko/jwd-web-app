package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class AfterSignUpPageCommand implements Command {

    private static final ResponseContext AFTER_SIGNUP_PAGE = () -> PathToPages.AFTER_SIGNUP_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return AFTER_SIGNUP_PAGE;
    }
}
