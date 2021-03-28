package com.epam.jwd.context;

/**
 * Class-storage that contain all path to jsp.
 *
 * @author Maxim Semenko
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
    public static final String SHOW_USERS_BY_CRITERIA_PAGE = "/WEB-INF/jsp/view/admin/show-users-by-criteria.jsp";
    public static final String FIND_USERS_BY_CRITERIA = "/WEB-INF/jsp/view/admin/show-users-by-criteria.jsp";


    // Redirect-commands
    public static final String HOME_REDIRECT = "/controller?command=home";
    public static final String USER_CABINET_REDIRECT = "/controller?command=cabinet";
    public static final String ADMIN_CABINET_REDIRECT = "/controller?command=admin";
    public static final String AFTER_SIGNUP_REDIRECT = "/controller?command=after-sign-up";
    public static final String AFTER_SEND_MESSAGE_PAGE_REDIRECT = "/controller?command=new-password";
    public static final String EDIT_FACULTY_REDIRECT = "/controller?command=cabinet/edit-faculty-page";
    public static final String FIND_USERS_BY_CRITERIA_REDIRECT = "/controller?command=admin/search-page";
    public static final String SHOW_ALL_USERS_PAGE_REDIRECT = "/controller?command=admin/all-users";
    public static final String USER_EDIT_PAGE_REDIRECT = "/controller?command=cabinet/edit";

    // Subsidiary pages
    public static final String ERROR_PAGE = "/WEB-INF/jsp/view/subsidiary/error.jsp";
}
