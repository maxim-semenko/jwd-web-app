package com.epam.jwd.service;

import com.epam.jwd.context.config.EmailConfiguration;
import lombok.extern.log4j.Log4j2;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for restore password. It has two method.
 * The first send message to email address and
 * second generate check code for to check if an
 * account belongs to a user.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Log4j2
public class RestorePasswordEmailService implements EmailService {

    private static RestorePasswordEmailService instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private final EmailConfiguration emailConfiguration = EmailConfiguration.getInstance();

    private int code;
    private static final String textContent = "You have sent a password recovery request. \n" +
            "To continue, enter the verification code on the site: ";

    public static RestorePasswordEmailService getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new RestorePasswordEmailService();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * The method, which send message to user.
     *
     * @param address email address, who do we want to send a message
     * @throws MessagingException exception, if some problems with sending message
     */
    @Override
    public void sendMessage(String address) throws MessagingException {
        code = generateCode();
        log.info("Generated check code " + code + " for address " + address);

        Session mailSession = Session.getDefaultInstance(emailConfiguration.getPropertiesSessionDefaultInstance());
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress("myemail@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
        message.setSubject("Restore password");
        message.setText(textContent + code);

        Transport transport = mailSession.getTransport();
        transport.connect(emailConfiguration.getUsername(), emailConfiguration.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        log.info("Transport mail session closed");
    }

    /**
     * Method generate check code for email message.
     *
     * @return check code
     */
    private int generateCode() {
        return (int) (1000 + Math.random() * 8999);
    }

    public int getGenerateCode() {
        return code;
    }
}
