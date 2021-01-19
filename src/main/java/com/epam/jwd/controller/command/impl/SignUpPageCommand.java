package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that open signUp page.
 *
 * @version 0.0.1
 */

public class SignUpPageCommand implements Command {

    private static final ResponseContext SIGNUP_PAGE
            = new ResponseContextImpl(PathToPages.SIGNUP_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SIGNUP_PAGE;
    }
}
