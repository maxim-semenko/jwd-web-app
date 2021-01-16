package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

import javax.servlet.http.HttpSession;

/**
 * Class command that show home page.
 */

public final class HomePageCommand implements Command {

    private static final ResponseContextImpl HOME_PAGE
            = new ResponseContextImpl(PathToPages.HOME_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return HOME_PAGE;
    }
}
