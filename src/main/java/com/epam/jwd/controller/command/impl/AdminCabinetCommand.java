package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that show admin cabinet page.
 *
 * @version 0.0.1
 */

public class AdminCabinetCommand implements Command {

    private static final ResponseContext ADMIN_CABINET_PAGE
            = new ResponseContextImpl(PathToPages.ADMIN_CABINET_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("enrolledList", AppContext.isEnrolledList);
        return ADMIN_CABINET_PAGE;
    }
}
