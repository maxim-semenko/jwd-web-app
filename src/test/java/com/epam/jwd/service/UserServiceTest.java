package com.epam.jwd.service;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.EnumUserStatus;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.listener.InitListener;
import com.epam.jwd.pool.ConnectionPool;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletContextEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    static ConnectionPool connectionPool;
    static UserService userService;

    @BeforeClass
    public static void beforeClass() throws Exception {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        connectionPool = ConnectionPool.getInstance();
        userService = UserService.getInstance();
    }

    @Test
    public void testGetInstance() {
        UserService userService = UserService.getInstance();
        Assert.assertEquals(userService, UserService.getInstance());
    }

    @Test
    public void testCreateByParams() {
        Map<String, String> params = new HashMap<>();
        params.put("firstname", "Test");
        params.put("lastname", "Test");
        params.put("login", "test");
        params.put("password", "11111111");
        params.put("email", "test@gmail.com");
        params.put("averageScore", "100");
        params.put("russianScore", "100");
        params.put("mathScore", "100");
        params.put("physicsScore", "100");
        params.put("facultySelect", "1");
        User user = new User(new UserBuilder()
                .setFirstname("Test")
                .setLastname("Test")
                .setLogin("test")
                .setPassword("11111111")
                .setEmail("test@gmail.com")
                .setAverageScore(100)
                .setRussianExamScore(100)
                .setMathExamScore(100)
                .setPhysicsExamScore(100)
                .setFacultyId(1)
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));

        User testUser = userService.createByParams(params);

        user.setId(999);
        testUser.setId(999);

        Assert.assertEquals(user.toString(), testUser.toString());
    }

    @Test
    public void testInsert() throws ValidatorException, InterruptedException, SQLException {
        String number = String.valueOf((int)(1 + Math.random() * 999999999));
        User user = new User(new UserBuilder()
                .setFirstname("Test")
                .setLastname("Test")
                .setLogin("test" + number)
                .setPassword("11111111")
                .setEmail("test@gmail.com")
                .setAverageScore(100)
                .setRussianExamScore(100)
                .setMathExamScore(100)
                .setPhysicsExamScore(100)
                .setFacultyId(1)
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));

        userService.insert(user);
        Assert.assertNotNull(userService.selectById(user.getId()));
    }

    @Test
    public void testInsertThatReturnValidatorException() {
        boolean isError = false;
        String number = String.valueOf((int)(1 + Math.random() * 999999999));
        User user = new User(new UserBuilder()
                .setFirstname("Test")
                .setLastname("Test")
                .setLogin("test" + number)
                .setPassword("11111111")
                .setEmail("test@gmail.com")
                .setAverageScore(120)
                .setRussianExamScore(100)
                .setMathExamScore(100)
                .setPhysicsExamScore(100)
                .setFacultyId(1)
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));

        try {
            userService.insert(user);
        } catch (ValidatorException e) {
            isError = true;
            e.printStackTrace();
        }
        assertTrue(isError);
    }

    @Test
    public void testUpdateThatReturnValidatorException() {
        boolean isError = false;
        String number = String.valueOf((int)(1 + Math.random() * 999999999));
        User user = new User(new UserBuilder()
                .setFirstname("Test")
                .setLastname("Test")
                .setLogin("test" + number)
                .setPassword("11111111")
                .setEmail("test@gmail.com")
                .setAverageScore(120)
                .setRussianExamScore(100)
                .setMathExamScore(100)
                .setPhysicsExamScore(100)
                .setFacultyId(1)
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));

        try {
            userService.update(user);
        } catch (ValidatorException e) {
            isError = true;
            e.printStackTrace();
        }
        assertTrue(isError);
    }

    @Test
    public void testRemoveById() {
        userService.removeById(User.COUNT_ID);
        Assert.assertNull(userService.selectById(User.COUNT_ID));
    }

    @Test
    public void testInsertToEnrolledList() throws ValidatorException {
        userService.insertToEnrolledList(userService.selectAll());
        Assert.assertEquals(userService.getEnrolledList().size(), 3);
    }

    @Test
    public void testGetMaxId() {
        User.COUNT_ID = userService.getMaxId();
        Assert.assertEquals(userService.getMaxId(), User.COUNT_ID);
    }

    @Test
    public void testGetByCriteria() {
        UserCriteria criteria = UserCriteria.builder()
                .login("test")
                .build();
        Assert.assertEquals(userService.getByCriteria(criteria).get().getLogin(), "test");
    }

    @Test
    public void removeEnrolledList() throws ValidatorException {
        userService.insertToEnrolledList(userService.selectAll());
        userService.removeEnrolledList();
        Assert.assertEquals(userService.getEnrolledList().size(), 0);
    }


}