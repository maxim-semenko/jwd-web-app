package com.epam.jwd.entity;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    User user = new User(new UserBuilder()
            .setLogin("pro100max2")
            .setPassword("1111")
            .setEmail("makss@gmail.com")
            .setFirstname("Maxim")
            .setLastname("Semenko")
            .setAverageScore(80)
            .setRussianExamScore(10)
            .setMathExamScore(0)
            .setPhysicsExamScore(83)
            .setUserRole(EnumUserRole.CLIENT)
            .setUserStatus(EnumUserStatus.UNKNOWN)
            .setId(1)
            .setFacultyId(3));

    @Test
    public void testConstructorTest() {
        User user = new User(new UserBuilder()
                .setLogin("test6")
                .setPassword("2222")
                .setEmail("makss@gmail.com")
                .setFirstname("Maxim")
                .setLastname("Semenko")
                .setAverageScore(100)
                .setRussianExamScore(90)
                .setMathExamScore(90)
                .setPhysicsExamScore(83)
                .setUserRole(EnumUserRole.CLIENT)
                .setFacultyId(3));

        user.setId(1);

        String about = "User{login='test6', password='2222', email='makss@gmail.com', userRole=CLIENT, userStatus=null, " +
                "firstname='Maxim', lastname='Semenko', averageScore=100, russianExamScore=90, mathExamScore=90, " +
                "physicsExamScore=83, facultyId=3, id=1, sumExams=0}";

        Assert.assertEquals(about, user.toString());

    }

    @Test
    public void getLoginTest() {
        Assert.assertEquals(user.getLogin(), "pro100max2");
    }

    @Test
    public void getPasswordTest() {
        Assert.assertEquals(user.getPassword(), "1111");
    }

    @Test
    public void getEmailTest() {
        Assert.assertEquals(user.getEmail(), "makss@gmail.com");
    }

    @Test
    public void getRoleIdTest() {
        Assert.assertEquals(user.getUserRole(), EnumUserRole.CLIENT);
    }

    @Test
    public void getFirstnameTest() {
        Assert.assertEquals(user.getFirstname(), "Maxim");
    }

    @Test
    public void getLastnameTest() {
        Assert.assertEquals(user.getLastname(), "Semenko");
    }

    @Test
    public void getAverageScoreTest() {
        Assert.assertEquals(user.getAverageScore(), 80);
    }

    @Test
    public void getRussianExamScoreTest() {
        Assert.assertEquals(user.getRussianExamScore(), 10);
    }

    @Test
    public void getUserStatus() {
        Assert.assertEquals(user.getUserStatus(), EnumUserStatus.UNKNOWN);
    }

    @Test
    public void getFacultyId() {
        Assert.assertEquals(user.getFacultyId(), 3);
    }

    @Test
    public void getId() {
        user.setId(2);
        Assert.assertEquals(user.getId(), 2);
    }

    @Test
    public void getSumExams() {
        user.setSumExams(400);
        Assert.assertEquals(user.getSumExams(), 400);
    }
}