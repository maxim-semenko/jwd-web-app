package com.epam.jwd.validator;

import com.epam.jwd.entity.Faculty;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Validator class, that checks right of {@link Faculty} faculty.
 *
 * @version 0.0.1
 */

public class FacultyValidator implements Validator<Faculty> {

    private static FacultyValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);


    public static FacultyValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new FacultyValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method check, if {@link Faculty} faculty has valid count of places.
     *
     * @param faculty faculty object
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(Faculty faculty) {
        return CountPlacesValidator.getInstance().validate(faculty.getCountPlaces());
    }
}
