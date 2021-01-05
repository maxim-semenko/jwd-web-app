package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;

@Log4j2
public class RemoveUserByClientCommand implements Command {

    private static final ResponseContext HOME_REDIRECT = () -> PathToPages.HOME_REDIRECT;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService.getInstance().removeById(Integer.parseInt(requestContext.getParamList().get(1)));
        HttpSession session = requestContext.getHttpSession();
        session.setAttribute("User", null);
        log.info("User id = " + requestContext.getParamList().get(1) + " are removed by client");
        return HOME_REDIRECT;
    }
}
