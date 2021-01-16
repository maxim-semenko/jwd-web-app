package com.epam.jwd.context;

/**
 * Class-storage that contain all path to jsp.
 *
 * @version 0.0.1
 */

public class PathToPages {

    // User pages
    public static final String AFTER_SIGNUP_PAGE = "/WEB-INF/jsp/view/user/after-signup.jsp";
    public static final String HOME_PAGE = "/WEB-INF/jsp/view/user/home.jsp";
    public static final String SIGNUP_PAGE = "/WEB-INF/jsp/view/user/signup.jsp";
    public static final String USER_CABINET_PAGE = "/WEB-INF/jsp/view/user/user-cabinet.jsp";
    public static final String FORGOT_PASSWORD_PAGE = "/WEB-INF/jsp/view/user/forgot-password.jsp";
    public static final String CONFIRM_USER_REMOVE_PAGE = "/WEB-INF/jsp/view/user/confirm-user-remove.jsp";
    public static final String USER_EDIT_PAGE = "/WEB-INF/jsp/view/user/user-edit.jsp";
    public static final String NEW_PASSWORD_PAGE = "/WEB-INF/jsp/view/user/new-password.jsp";

    // Admin pages
    public static final String ADMIN_CABINET_PAGE = "/WEB-INF/jsp/view/admin/main-admin.jsp";
    public static final String SHOW_ALL_USERS_PAGE = "/WEB-INF/jsp/view/admin/all-users.jsp";
    public static final String SHOW_ENROLLED_LIST_PAGE = "/WEB-INF/jsp/view/admin/show-enrolled-list.jsp";
    public static final String EDIT_FACULTY_PAGE = "/WEB-INF/jsp/view/admin/edit-faculty.jsp";

    // Redirect-commands
    public static final String HOME_REDIRECT = "/home?command=home";
    public static final String USER_CABINET_REDIRECT = "/home?command=cabinet";
    public static final String ADMIN_CABINET_REDIRECT = "/home?command=admin";
    public static final String AFTER_SIGNUP_REDIRECT = "/home?command=after-sign-up";
    public static final String AFTER_SEND_MESSAGE_PAGE_REDIRECT = "/home?command=new-password";
    public static final String EDIT_FACULTY_REDIRECT = "/home?command=cabinet/edit-faculty-page";

    // Subsidiary pages
    public static final String ERROR_PAGE = "/WEB-INF/jsp/view/subsidiary/error.jsp";
}
