package com.epam.jwd;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.context.CommandType;
import com.epam.jwd.entity.EnumUserStatus;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ExecuteRepositoryException;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.PasswordSecurityService;
import com.epam.jwd.service.RestorePasswordEmailService;
import com.epam.jwd.service.UserService;

import javax.mail.MessagingException;

public class Main {

    public static void main(String[] args) {
        AppContext.getInstance().init();
        try {
            RestorePasswordEmailService.getInstance().sendMessage("maks.semenko@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


