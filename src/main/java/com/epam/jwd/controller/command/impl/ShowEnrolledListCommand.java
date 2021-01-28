package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.UserService;

/**
 * Class command that show enrolled list.
 *
 * @version 0.0.1
 */

public class ShowEnrolledListCommand implements Command {

    private static final ResponseContext SHOW_ENROLLED_LIST_PAGE
            = new ResponseContextImpl(PathToPages.SHOW_ENROLLED_LIST_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("showEnrolledList", UserService.getInstance().getEnrolledList());
        return SHOW_ENROLLED_LIST_PAGE;
    }
}
