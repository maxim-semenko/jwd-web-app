package com.epam.jwd.validator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Validator class, that checks valid of {@link Integer} count places.
 *
 * @version 0.0.1
 */

public class CountPlacesValidator implements Validator<Integer> {

    private static CountPlacesValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);


    public static CountPlacesValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new CountPlacesValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * @param input count of places
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(Integer input) {
        Predicate<Integer> integerPredicate = integer -> integer > 0;
        return integerPredicate.test(input);
    }
}
