package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that show page after user signup.
 */

public class AfterSignUpPageCommand implements Command {

    private static final ResponseContext AFTER_SIGNUP_PAGE
            = new ResponseContextImpl(PathToPages.AFTER_SIGNUP_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return AFTER_SIGNUP_PAGE;
    }
}
