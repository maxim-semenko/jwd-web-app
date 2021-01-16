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
import com.epam.jwd.controller.command.impl.HomePageCommand;
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

import java.util.Arrays;
import java.util.Optional;

/**
 * {@link CommandFactory} CommandFactory class,
 * which return needed {@link Command} command.
 */

@Log4j2
public class CommandFactory {

    /**
     * Method return {@link Command} command
     *
     * @param url {@link String} const value
     * @return {@link Command} command
     */
    public static Command getCommand(final String url) {
        Optional<CommandType> optional =
                Arrays.stream(CommandType.values())
                        .filter(enumCommand -> enumCommand.getUrl().equals(url)).findFirst();
        if (optional.isPresent()) {
            log.info("Get page " + url);
            return optional.get().getCommand();
        }
        return new ErrorCommand();
    }
}
