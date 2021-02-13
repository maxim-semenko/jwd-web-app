package com.epam.jwd.service;

import com.epam.jwd.dao.impl.FacultyDao;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.validator.FacultyValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link FacultyService} FacultyService, which is intended for business logic
 * of working with {@link Faculty} faculty data.
 * It accesses to {@link FacultyDao} FacultyDao.
 *
 * @version 0.0.1
 */

public class FacultyService {

    public static FacultyService instance;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static final FacultyDao facultyDao = FacultyDao.getInstance();

    public static FacultyService getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new FacultyService();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Method select faculty by id.
     *
     * @param id value for select {@link Faculty}
     * @return {@link Faculty} faculty
     */
    public Faculty selectById(Integer id) {
        return facultyDao.selectById(id);
    }

    /**
     * Method select all faculties from table.
     *
     * @return {@link List<Faculty>} faculty list
     */
    public List<Faculty> selectAll() {
        return facultyDao.selectAll();
    }

    /**
     * Method that inserts {@link Faculty}.
     *
     * @param faculty need to insert
     * @throws ValidatorException if data is not valid
     */
    public void insert(Faculty faculty) throws ValidatorException {
        if (FacultyValidator.getInstance().validate(faculty)) {
            facultyDao.insert(faculty);
        } else {
            throw new ValidatorException("Faculty validator exception");
        }
    }

    /**
     * Method that updates {@link Faculty}.
     *
     * @param faculty {@link Faculty} need to update
     * @throws ValidatorException if data is not valid
     */
    public void update(Faculty faculty) throws ValidatorException {
        if (FacultyValidator.getInstance().validate(faculty)) {
            facultyDao.update(faculty);
        } else {
            throw new ValidatorException("Faculty validator exception");
        }
    }


}
