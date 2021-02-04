package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class, that checks right of {@link String} password.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class PasswordValidator implements Validator<String> {

    private static PasswordValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    public static PasswordValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new PasswordValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method check, if {@link String} password has right length.
     *
     * @param input user password
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(String input) {
        Predicate<String> stringPredicate = str -> (str.length() > 7 && str.length() < 256);
        return stringPredicate.test(input);
    }
}
