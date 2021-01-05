package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.service.RestorePasswordEmailService;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Log4j2
public class SendEmailMessageCommand implements Command {

    private static final ResponseContext AFTER_SEND_MESSAGE_PAGE_REDIRECT = () -> PathToPages.AFTER_SEND_MESSAGE_PAGE_REDIRECT;
    private static final ResponseContext FORGOT_PASSWORD_PAGE = () -> PathToPages.FORGOT_PASSWORD_PAGE;


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("ErrorMessage", false);
        requestContext.setAttribute("NotFoundUser", false);

        Optional<User> optionalUser = getOptionalUser(requestContext);
        if (optionalUser.isPresent()) {
            try {
                HttpSession session = requestContext.getHttpSession();
                RestorePasswordEmailService service = RestorePasswordEmailService.getInstance();
                service.sendMessage(optionalUser.get().getEmail());
                session.setAttribute("UserForRestorePassword", optionalUser.get());
                session.setAttribute("CheckCode", service.getGenerateCode());
            } catch (MessagingException e) {
                log.error("Can't send message " + e);
            }
        } else {
            requestContext.setAttribute("NotFoundUser", true);
            return FORGOT_PASSWORD_PAGE;
        }
        return AFTER_SEND_MESSAGE_PAGE_REDIRECT;
    }

    private Optional<User> getOptionalUser(RequestContext requestContext) {
        return UserService
                .getInstance()
                .getByCriteria(UserCriteria.builder().login(requestContext.getParamList().get(1)).build());
    }

}
