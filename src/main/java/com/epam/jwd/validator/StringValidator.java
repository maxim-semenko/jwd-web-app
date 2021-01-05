package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class, that checks right of {@link String} data.
 *
 * @version 0.0.1
 */

public class StringValidator implements Validator<String> {

    private static StringValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private final int maxLength = 25;
    private final int minxLength = 2;

    public static StringValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new StringValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method check if {@link String} input not empty and have right length.
     *
     * @param input user data
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(final String input) {
        Predicate<String> stringPredicate = str -> (
                !str.isEmpty()
                        & str.length() < maxLength
                        & str.length() > minxLength
        );
        return stringPredicate.test(input);
    }
}
