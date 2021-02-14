package com.epam.jwd.validator;

import com.epam.jwd.entity.User;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Validator class that checks right of {@link User} data.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class UserValidator implements Validator<User> {


    public static UserValidator instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private static final ScoreValidator scoreValidator = ScoreValidator.getInstance();


    public static UserValidator getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new UserValidator();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method that checks, if all input data of {@link User}.
     *
     * @param user {@link User}
     * @return {@link Boolean} true/false
     */
    @Override
    public Boolean validate(final User user) {
        return LoginValidator.getInstance().validate(user.getLogin())
                && PasswordValidator.getInstance().validate(user.getPassword())
                && NameValidator.getInstance().validate(user.getFirstname())
                && NameValidator.getInstance().validate(user.getLastname())
                && EmailValidator.getInstance().validate(user.getEmail())
                && scoreValidator.validate(user.getAverageScore())
                && scoreValidator.validate(user.getRussianExamScore())
                && scoreValidator.validate(user.getMathExamScore())
                && scoreValidator.validate(user.getPhysicsExamScore());
    }


}
