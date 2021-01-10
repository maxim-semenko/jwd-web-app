package com.epam.jwd.controller.command;

import com.epam.jwd.context.CommandType;
import com.epam.jwd.controller.command.impl.AcceptUserEditCommand;
import com.epam.jwd.controller.command.impl.AdminCabinetCommand;
import com.epam.jwd.controller.command.impl.AfterSignUpPageCommand;
import com.epam.jwd.controller.command.impl.ChangePasswordCommand;
import com.epam.jwd.controller.command.impl.ConfirmUserRemoveCommand;
import com.epam.jwd.controller.command.impl.EditFacultyCommand;
import com.epam.jwd.controller.command.impl.EditFacultyPageCommand;
import com.epam.jwd.controller.command.impl.ErrorCommand;
import com.epam.jwd.controller.command.impl.ForgotPasswordPageCommand;
import com.epam.jwd.controller.command.impl.GenerateEnrolledListCommand;
import com.epam.jwd.controller.command.impl.HomeCommand;
import com.epam.jwd.controller.command.impl.NewPasswordPageCommand;
import com.epam.jwd.controller.command.impl.RemoveAllUsersCommand;
import com.epam.jwd.controller.command.impl.RemoveEnrolledListCommand;
import com.epam.jwd.controller.command.impl.RemoveUserByAdminCommand;
import com.epam.jwd.controller.command.impl.RemoveUserByClientCommand;
import com.epam.jwd.controller.command.impl.SendEmailMessageCommand;
import com.epam.jwd.controller.command.impl.ShowAllUsersPageCommand;
import com.epam.jwd.controller.command.impl.ShowEnrolledListCommand;
import com.epam.jwd.controller.command.impl.SignInCommand;
import com.epam.jwd.controller.command.impl.SignOutCommand;
import com.epam.jwd.controller.command.impl.SignUpPageCommand;
import com.epam.jwd.controller.command.impl.SignUpUserCommand;
import com.epam.jwd.controller.command.impl.UserCabinetCommand;
import com.epam.jwd.controller.command.impl.UserEditPageCommand;
import lombok.extern.log4j.Log4j2;

/**
 * {@link CommandFactory} CommandFactory class,
 * which return needed {@link Command} command.
 */

@Log4j2
public class CommandFactory {

    /**
     * Method return {@link Command} command
     *
     * @param command {@link String} const value
     * @return {@link Command} command
     */
    public static Command getCommand(final String command) {
        try {
            log.info("Try get " + command);
            switch (CommandType.valueOf(command)) {
                case ACCEPT_USER_EDIT:
                    return new AcceptUserEditCommand();
                case ADMIN_CABINET_PAGE:
                    return new AdminCabinetCommand();
                case AFTER_SIGNUP_PAGE:
                    return new AfterSignUpPageCommand();
                case CHANGE_PASSWORD:
                    return new ChangePasswordCommand();
                case CONFIRM_USER_REMOVE_PAGE:
                    return new ConfirmUserRemoveCommand();
                case EDIT_FACULTY_PAGE:
                    return new EditFacultyPageCommand();
                case EDIT_FACULTY:
                    return new EditFacultyCommand();
                case FORGOT_PASSWORD_PAGE:
                    return new ForgotPasswordPageCommand();
                case GENERATE_ENROLLED_LIST:
                    return new GenerateEnrolledListCommand();
                case HOME_PAGE:
                    return new HomeCommand();
                case NEW_PASSWORD_PAGE:
                    return new NewPasswordPageCommand();
                case REMOVE_ALL_USERS:
                    return new RemoveAllUsersCommand();
                case REMOVE_USER_BY_CLIENT:
                    return new RemoveUserByClientCommand();
                case REMOVE_USER_BY_ADMIN:
                    return new RemoveUserByAdminCommand();
                case REMOVE_ENROLLED_LIST:
                    return new RemoveEnrolledListCommand();
                case SEND_EMAIL_MESSAGE:
                    return new SendEmailMessageCommand();
                case SIGN_IN:
                    return new SignInCommand();
                case SIGN_OUT:
                    return new SignOutCommand();
                case SIGN_UP_PAGE:
                    return new SignUpPageCommand();
                case SIGN_UP_USER:
                    return new SignUpUserCommand();
                case SHOW_ALL_USERS:
                    return new ShowAllUsersPageCommand();
                case SHOW_ENROLLED_LIST:
                    return new ShowEnrolledListCommand();
                case USER_CABINET:
                    return new UserCabinetCommand();
                case USER_EDIT_PAGE:
                    return new UserEditPageCommand();
            }
        } catch (IllegalArgumentException e) {
            log.error("Incorrect command " + e);
        }
        return new ErrorCommand();
    }
}
