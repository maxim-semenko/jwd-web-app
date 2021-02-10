package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

/**
 * Class command that remove {@link User} by client.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Log4j2
public class RemoveUserByClientCommand implements Command {

    private static final ResponseContext HOME_REDIRECT
            = new ResponseContextImpl(PathToPages.HOME_REDIRECT, ResponseContext.ResponseType.REDIRECT);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService.getInstance().removeById(Integer.parseInt(requestContext.getParamMap().get("id")));
        requestContext.getHttpSession().setAttribute("user", null);

        log.info("User id = " + requestContext.getParamMap().get("id") + " are removed by client");
        return HOME_REDIRECT;
    }
}
