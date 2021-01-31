package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;

public class ShowUsersByCriteriaPageCommand implements Command {

    private static final ResponseContext SHOW_USERS_BY_CRITERIA_PAGE
            = new ResponseContextImpl(PathToPages.SHOW_USERS_BY_CRITERIA_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("usersByCriteria",
                requestContext.getHttpSession().getAttribute("usersByCriteria"));
        requestContext.getHttpSession().setAttribute("usersByCriteria", null);
        requestContext.getHttpSession().setAttribute("adminPage", "searchUsers");
        return SHOW_USERS_BY_CRITERIA_PAGE;
    }
}
