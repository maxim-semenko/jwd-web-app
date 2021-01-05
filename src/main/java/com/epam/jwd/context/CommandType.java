package com.epam.jwd.context;

import com.epam.jwd.controller.command.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * Class-storage that contain all application command type.
 *
 * @version 0.0.1
 */

public enum CommandType {
    ADMIN_CABINET_PAGE(),
    ACCEPT_USER_EDIT,
    AFTER_SIGNUP_PAGE,
    NEW_PASSWORD_PAGE,
    CONFIRM_USER_REMOVE_PAGE,
    CHANGE_PASSWORD,
    EDIT_FACULTY,
    EDIT_FACULTY_PAGE,
    FORGOT_PASSWORD_PAGE,
    GENERATE_ENROLLED_LIST,
    HOME_PAGE,
    REMOVE_ALL_USERS,
    REMOVE_USER_BY_ADMIN,
    REMOVE_USER_BY_CLIENT,
    REMOVE_ENROLLED_LIST,
    SEND_EMAIL_MESSAGE,
    SIGN_UP,
    SHOW_ALL_USERS,
    SHOW_ENROLLED_LIST,
    SIGN_IN,
    SIGN_OUT,
    USER_CABINET,
    USER_EDIT_PAGE;
}
