package com.epam.jwd.service;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that performs the function of hashing passwords.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Log4j2
public class PasswordSecurityService {

    public static PasswordSecurityService instance;

    public static PasswordSecurityService getInstance() {
        if (instance == null) {
            instance = new PasswordSecurityService();
        }
        return instance;
    }

    /**
     * Method hashing password.
     *
     * @param password {@link String} in text format
     * @return {@link String} in MD5 format
     */
    public String doHashing(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("Can't hash password ", e);
        }
        return sb.toString();
    }

}
