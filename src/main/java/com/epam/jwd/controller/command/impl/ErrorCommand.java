package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that show 404 error page.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class ErrorCommand implements Command {

    private static final ResponseContext ERROR_PAGE
            = new ResponseContextImpl(PathToPages.ERROR_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return ERROR_PAGE;
    }
}
