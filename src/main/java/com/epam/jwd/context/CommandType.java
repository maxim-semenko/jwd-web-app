package com.epam.jwd.context;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.impl.AcceptUserEditCommand;
import com.epam.jwd.controller.command.impl.AdminCabinetCommand;
import com.epam.jwd.controller.command.impl.AfterSignUpPageCommand;
import com.epam.jwd.controller.command.impl.ChangePasswordCommand;
import com.epam.jwd.controller.command.impl.ConfirmUserRemoveCommand;
import com.epam.jwd.controller.command.impl.EditFacultyCommand;
import com.epam.jwd.controller.command.impl.EditFacultyPageCommand;
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

/**
 * Class-storage that contain all application command type.
 *
 * @version 0.0.1
 */

public enum CommandType {

    ADMIN_CABINET_PAGE("admin", new AdminCabinetCommand()),
    ACCEPT_USER_EDIT("accept-user-edit", new AcceptUserEditCommand()),
    AFTER_SIGNUP_PAGE("after-sign-up", new AfterSignUpPageCommand()),
    CONFIRM_USER_REMOVE_PAGE("cabinet/remove", new ConfirmUserRemoveCommand()),
    CHANGE_PASSWORD("change-password", new ChangePasswordCommand()),
    EDIT_FACULTY("edit-faculty", new EditFacultyCommand()),
    EDIT_FACULTY_PAGE("cabinet/edit-faculty-page", new EditFacultyPageCommand()),
    FORGOT_PASSWORD_PAGE("forgot-password", new ForgotPasswordPageCommand()),
    GENERATE_ENROLLED_LIST("generate-enrolled-list", new GenerateEnrolledListCommand()),
    HOME_PAGE("home", new HomePageCommand()),
    NEW_PASSWORD_PAGE("new-password", new NewPasswordPageCommand()),
    REMOVE_ALL_USERS("remove-all-users", new RemoveAllUsersCommand()),
    REMOVE_USER_BY_ADMIN("remove-user-by-admin", new RemoveUserByAdminCommand()),
    REMOVE_USER_BY_CLIENT("remove-user-by-client", new RemoveUserByClientCommand()),
    REMOVE_ENROLLED_LIST("remove-enrolled-list", new RemoveEnrolledListCommand()),
    SEND_EMAIL_MESSAGE("send-message", new SendEmailMessageCommand()),
    SIGN_UP_PAGE("sign-up", new SignUpPageCommand()),
    SIGN_UP_USER("sign-up-user", new SignUpUserCommand()),
    SHOW_ALL_USERS("admin/all-users", new ShowAllUsersPageCommand()),
    SHOW_ENROLLED_LIST("admin/enrolled-list", new ShowEnrolledListCommand()),
    SIGN_IN("sign-in", new SignInCommand()),
    SIGN_OUT("sign-out", new SignOutCommand()),
    USER_CABINET("cabinet", new UserCabinetCommand()),
    USER_EDIT_PAGE("cabinet/edit", new UserEditPageCommand());

    private final String url;
    private final Command command;

    CommandType(final String url, final Command command) {
        this.url = url;
        this.command = command;
    }

    public String getUrl() {
        return url;
    }

    public Command getCommand() {
        return command;
    }


}
