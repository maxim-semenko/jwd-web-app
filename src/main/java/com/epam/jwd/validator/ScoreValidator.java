package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class that checks right of {@link Integer} user score exam.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class ScoreValidator implements Validator<Integer> {

    private static ScoreValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private final int max = 101;
    private final int min = -1;


    public static ScoreValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new ScoreValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method checks, if {@link Integer} input has a range from 0 to 100.
     *
     * @param input {@link Integer} user's score
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(final Integer input) {
        Predicate<Integer> integerPredicate = integer -> integer > min && integer < max;
        return integerPredicate.test(input);
    }
}
