package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class command that show search by criteria page.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class ShowUsersByCriteriaPageCommand implements Command {

    private static final ResponseContext SHOW_USERS_BY_CRITERIA_PAGE
            = new ResponseContextImpl(PathToPages.SHOW_USERS_BY_CRITERIA_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_USERS_BY_CRITERIA_PAGE;
    }
}
