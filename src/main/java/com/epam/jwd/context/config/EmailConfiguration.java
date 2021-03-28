package com.epam.jwd.context.config;

import com.epam.jwd.util.EmailPropertiesReaderUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class DatabaseConfiguration that stores
 * configuration and allows to take values.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Log4j2
@Getter
@ToString
public class EmailConfiguration {

    private static EmailConfiguration instance;

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASSWORD = "mail.password";

    private final Properties propertiesSessionDefaultInstance = new Properties();
    private String username;
    private String password;


    public static EmailConfiguration getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    /**
     * Method performs initialization configurations of email.
     *
     * @return {@EmailConfiguration} singleton instance with properties
     */
    private static EmailConfiguration init() {
        EmailConfiguration instance = new EmailConfiguration();

        log.info("Start email configuration");
        instance.propertiesSessionDefaultInstance.put(MAIL_SMTP_AUTH, EmailPropertiesReaderUtil.resourceBundle.getObject(MAIL_SMTP_AUTH));
        instance.propertiesSessionDefaultInstance.put(MAIL_SMTP_STARTTLS_ENABLE, EmailPropertiesReaderUtil.resourceBundle.getObject(MAIL_SMTP_STARTTLS_ENABLE));
        instance.propertiesSessionDefaultInstance.put(MAIL_SMTP_HOST, EmailPropertiesReaderUtil.resourceBundle.getString(MAIL_SMTP_HOST));
        instance.propertiesSessionDefaultInstance.put(MAIL_SMTP_PORT, EmailPropertiesReaderUtil.resourceBundle.getString(MAIL_SMTP_PORT));
        instance.username = EmailPropertiesReaderUtil.resourceBundle.getString(MAIL_USERNAME);
        instance.password = EmailPropertiesReaderUtil.resourceBundle.getString(MAIL_PASSWORD);

        return instance;
    }


}
