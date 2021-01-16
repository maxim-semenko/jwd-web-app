package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that show forgot password page.
 *
 * @version 0.0.1
 */

public class ForgotPasswordPageCommand implements Command {

    private static final ResponseContext FORGOT_PASSWORD_PAGE
            = new ResponseContextImpl(PathToPages.FORGOT_PASSWORD_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return FORGOT_PASSWORD_PAGE;
    }
}
