package com.epam.jwd.context.config;

import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.util.AdminPropertiesReaderUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Admin, which has admin opportunity to control web app.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Getter
@ToString
@Log4j2
public final class AdminConfiguration {

    private static AdminConfiguration instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String ROLE_ID = "roleId";

    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private int roleId;

    public static AdminConfiguration getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = init();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method performs initialization configurations of data base.
     *
     * @return singleton instance of class
     */
    private static AdminConfiguration init() {
        AdminConfiguration instance = new AdminConfiguration();

        log.info("Start email configuration");
        instance.login = AdminPropertiesReaderUtil.resourceBundle.getString(LOGIN);
        instance.password = AdminPropertiesReaderUtil.resourceBundle.getString(PASSWORD);
        instance.email = AdminPropertiesReaderUtil.resourceBundle.getString(EMAIL);
        instance.firstname = AdminPropertiesReaderUtil.resourceBundle.getString(FIRSTNAME);
        instance.lastname = AdminPropertiesReaderUtil.resourceBundle.getString(LASTNAME);
        instance.roleId = Integer.parseInt(AdminPropertiesReaderUtil.resourceBundle.getString(ROLE_ID));

        return instance;
    }

    /**
     * Method that build and return {@link User} admin.
     *
     * @return {@link User} admin
     */
    public User getAdmin() {
        return new User(new UserBuilder()
                .setFirstname(AdminConfiguration.getInstance().getFirstname())
                .setLastname(AdminConfiguration.getInstance().getLastname())
                .setLogin(AdminConfiguration.getInstance().getLogin())
                .setPassword(AdminConfiguration.getInstance().getPassword())
                .setEmail(AdminConfiguration.getInstance().getEmail())
                .setId(0)
                .setUserRole(EnumUserRole.resolveById(AdminConfiguration.getInstance().getRoleId())));
    }

}
