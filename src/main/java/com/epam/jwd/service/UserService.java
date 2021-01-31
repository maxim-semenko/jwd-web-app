package com.epam.jwd.service;

import com.epam.jwd.dao.impl.UserDao;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.EnumUserStatus;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.validator.UserValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link UserService} UserService, which is intended for business logic
 * of working with {@link User} user data.
 * It accesses to {@link UserDao} UserDao.
 *
 * @version 0.0.1
 */

public class UserService {

    public static UserService instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private static final UserDao userDao = UserDao.getInstance();

    public static UserService getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new UserService();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method that creates {@link User}.
     *
     * @param params {@link Map<>} for {@link User}
     * @return {@link User}
     */
    public User createByParams(final Map<String, String> params) {
        return new User(new UserBuilder()
                .setFirstname(params.get("firstname"))
                .setLastname(params.get("lastname"))
                .setLogin(params.get("login"))
                .setPassword(params.get("password"))
                .setEmail(params.get("email"))
                .setAverageScore(Integer.parseInt(params.get("averageScore")))
                .setRussianExamScore(Integer.parseInt(params.get("russianScore")))
                .setMathExamScore(Integer.parseInt(params.get("mathScore")))
                .setPhysicsExamScore(Integer.parseInt(params.get("physicsScore")))
                .setFacultyId(Integer.parseInt(params.get("facultySelect")))
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));
    }

    /**
     * Method, which insert {@link User} to table app_user.
     *
     * @param user insert object
     * @throws ValidatorException if not valid date
     */
    public void insert(User user) throws ValidatorException {
        if (UserValidator.getInstance().validate(user)) {
            user.setPassword(PasswordSecurityService.getInstance().doHashing(user.getPassword()));
            userDao.insert(user);
        } else {
            throw new ValidatorException("User validator exception");
        }
    }

    /**
     * Method, which update {@link User} in table app_user.
     *
     * @param user insert object
     * @throws ValidatorException if not valid date
     */
    public void update(User user) throws ValidatorException {
        if (UserValidator.getInstance().validate(user)) {
            userDao.update(user);
        } else {
            throw new ValidatorException("User validator exception");
        }
    }

    /**
     * Method, which select {@link User} by id in table app_user.
     *
     * @param id the value for search in table
     * @return {@link User} object, which we want to get by id
     */
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    /**
     * Method, which select {@link User} all users fro, table app_user.
     *
     * @return {@link List<User>} all users
     */
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    /**
     * Method delete {@link User} user by id from table.
     */
    public void removeById(Integer id) {
        userDao.removeById(id);
    }

    /**
     * Method delete {@link User} all users from table.
     */
    public void removeAllUsers() {
        userDao.removeAllUsers();
    }

    /**
     * Method delete all users from enrolled list and
     * update to {@link EnumUserStatus} unknown status.
     *
     * @throws ValidatorException if update has problems
     */
    public void removeEnrolledList() throws ValidatorException {
        userDao.removeAllFromEnrolledList();
        List<User> userList = selectAll();
        for (User user : userList) {
            user.setUserStatus(EnumUserStatus.UNKNOWN);
            update(user);
        }
    }

    /**
     * The method gets {@link User} all users, calculates the sum of points,
     * sorts by points and then leaves the required amount. If the
     * user is enrolled, his {@link EnumUserStatus } status is update
     * to enrolled, else no enrolled.
     *
     * @param userList all users
     * @throws ValidatorException if update has problems
     */
    public void insertToEnrolledList(List<User> userList) throws ValidatorException {
        prepareUsersForEntrant(userList);
        userList = sort(userList);
        List<User> enrolledList = new ArrayList<>();
        for (Faculty faculty : FacultyService.getInstance().selectAll()) {
            List<User> list = userList
                    .stream()
                    .filter(user -> user.getFacultyId() == faculty.getType().getId())
                    .limit(faculty.getCountPlaces())
                    .collect(Collectors.toList());
            enrolledList.addAll(list);
        }
        for (User user : enrolledList) {
            user.setUserStatus(EnumUserStatus.ENROLLED);
            update(user);
            userDao.insertToEnrolledList(user);
        }
    }

    /**
     * Method return last id in user table in database.
     *
     * @return {@link Integer} id
     */
    public int getMaxId() {
        return userDao.getMaxId();
    }

    /**
     * Method return count of user enrolled.
     *
     * @return {@link Integer} count of user enrolled
     */
    public Integer getCountUserEnrolled() {
        return userDao.getCountUserEnrolled();
    }

    /**
     * Method calculate {@link Integer} user sum exams.
     *
     * @param user {@link User}
     * @return {@link Integer} sum exams
     */
    public Integer getSumExams(User user) {
        return user.getAverageScore()
                + user.getRussianExamScore()
                + user.getMathExamScore()
                + user.getPhysicsExamScore();
    }

    private Stream<User> getCriteria(UserCriteria uc) {
        return userDao.selectAll()
                .stream()
                .filter(user -> uc.getFirstname() == null || uc.getFirstname().equals(user.getFirstname()))
                .filter(user -> uc.getLastname() == null || uc.getLastname().equals(user.getLastname()))
                .filter(user -> uc.getLogin() == null || uc.getLogin().equals(user.getLogin()))
                .filter(user -> uc.getPassword() == null || PasswordSecurityService.getInstance().doHashing(uc.getPassword()).equals(user.getPassword()))
                .filter(user -> uc.getEmail() == null || uc.getEmail().equals(user.getEmail()))
                .filter(user -> uc.getId() == 0 || uc.getId() == user.getId())
                .filter(user -> uc.getAverageScore() == 0 || uc.getAverageScore() == user.getAverageScore())
                .filter(user -> uc.getRussianExamScore() == 0 || uc.getRussianExamScore() == user.getRussianExamScore())
                .filter(user -> uc.getMathExamScore() == 0 || uc.getMathExamScore() == user.getAverageScore())
                .filter(user -> uc.getPhysicsExamScore() == 0 || uc.getPhysicsExamScore() == user.getPhysicsExamScore())
                .filter(user -> uc.getFacultyId() == 0 || uc.getFacultyId() == user.getFacultyId());
    }

    /**
     * Method finds {@link User} user by {@link UserCriteria} criteria.
     *
     * @param uc {@link UserCriteria} contain all param of {@link User}
     * @return {@link Optional<User>} optional of user
     */
    public Optional<User> getByCriteria(UserCriteria uc) {
        return getCriteria(uc).findFirst();
    }

    /**
     * Method finds {@link User} all user by {@link UserCriteria} criteria.
     *
     * @param uc {@link UserCriteria} contain all param of {@link User}
     * @return {@link List<User>} optional of user
     */
    public List<User> getAllByCriteria(UserCriteria uc) {
        return getCriteria(uc).collect(Collectors.toList());
    }

    /**
     * Method that return enrolled list.
     *
     * @return {@link List<User>} enrolled list
     */
    public List<User> getEnrolledList() {
        List<User> userList = new ArrayList<>();
        Map<Integer, Integer> map = userDao.selectAllEnrolledList();
        for (Integer key : map.keySet()) {
            User temp = selectById(key);
            temp.setSumExams(map.get(key));
            userList.add(temp);
        }
        return sort(userList);
    }

    /**
     * Method calculate {@link Integer} user sum exams,
     * change {@link EnumUserStatus} user status
     * and update in database.
     *
     * @param userList all Users
     * @throws ValidatorException if user data not valid
     */
    private void prepareUsersForEntrant(List<User> userList) throws ValidatorException {
        for (User user : userList) {
            user.setSumExams(getSumExams(user));
            user.setUserStatus(EnumUserStatus.NO_ENROLLED);
            update(user);
        }
    }

    /**
     * Method sort {@link List<User>}
     *
     * @param userList list
     * @return sorted {@link List<User>}
     */
    private List<User> sort(List<User> userList) {
        return userList
                .stream()
                .sorted(Comparator.comparingInt(User::getSumExams).reversed())
                .collect(Collectors.toList());
    }


}
