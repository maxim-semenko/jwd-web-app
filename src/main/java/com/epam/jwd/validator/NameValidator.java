package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class, that checks right of {@link String} name.
 *
 * @version 0.0.1
 */

public class NameValidator implements Validator<String> {

    private static NameValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private final static String name_regex_pattern = "^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$";

    public static NameValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new NameValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method check, if {@link String} name not empty and looks like match pattern.
     *
     * @param input user password
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(String input) {
        Predicate<String> stringPredicate = str -> (
                StringValidator.getInstance().validate(str) & str.matches(name_regex_pattern)
        );
        return stringPredicate.test(input);
    }
}
