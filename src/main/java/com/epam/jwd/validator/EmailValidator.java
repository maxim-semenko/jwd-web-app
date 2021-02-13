package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class, that checks right of {@link String} user email.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class EmailValidator implements Validator<String> {

    private static EmailValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private final String EMAIL_REGEX_PATTERN = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";


    public static EmailValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new EmailValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }


    /**
     * Method check, if {@link String} input looks like match pattern.
     *
     * @param input {@link String} user's email
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(final String input) {
        Predicate<String> stringPredicate = str -> (
                StringValidator.getInstance().validate(str) & str.matches(EMAIL_REGEX_PATTERN)
        );
        return stringPredicate.test(input);
    }
}
