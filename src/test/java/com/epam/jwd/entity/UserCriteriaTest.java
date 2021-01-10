package com.epam.jwd.entity;

import org.junit.Assert;
import org.junit.Test;

public class UserCriteriaTest {

    UserCriteria userCriteria = UserCriteria.builder()
            .login("Test")
            .password("12345678")
            .email("test@gmail.com")
            .userRole(EnumUserRole.CLIENT)
            .firstname("TestFirstname")
            .lastname("TestLastname")
            .averageScore(100)
            .russianExamScore(100)
            .mathExamScore(100)
            .physicsExamScore(100)
            .facultyId(1)
            .id(99)
            .sumExams(400)
            .build();


    @Test
    public void testTestToString() {
        UserCriteria userCriteria = UserCriteria.builder()
                .login("Test")
                .password("12345678").build();
        String test = "UserCriteria(login=Test, password=12345678, email=null, userRole=null, firstname=null, " +
                "lastname=null, averageScore=0, russianExamScore=0, mathExamScore=0, physicsExamScore=0, " +
                "facultyId=0, id=0, sumExams=0)";
        Assert.assertEquals(userCriteria.toString(), test);
    }

    @Test
    public void testBuilder() {
        UserCriteria userCriteria = UserCriteria.builder()
                .login("Test")
                .password("12345678").build();
    }

    @Test
    public void testGetLogin() {
        Assert.assertEquals(userCriteria.getLogin(), "Test");
    }

    @Test
    public void testGetPassword() {
        Assert.assertEquals(userCriteria.getPassword(), "12345678");

    }

    @Test
    public void testGetEmail() {
        Assert.assertEquals(userCriteria.getEmail(), "test@gmail.com");

    }

    @Test
    public void testGetUserRole() {
        Assert.assertEquals(userCriteria.getUserRole(), EnumUserRole.CLIENT);

    }

    @Test
    public void testGetFirstname() {
        Assert.assertEquals(userCriteria.getFirstname(), "TestFirstname");

    }

    @Test
    public void testGetLastname() {
        Assert.assertEquals(userCriteria.getLastname(), "TestLastname");

    }

    @Test
    public void testGetAverageScore() {
        Assert.assertEquals(userCriteria.getAverageScore(), 100);

    }

    @Test
    public void testGetRussianExamScore() {
        Assert.assertEquals(userCriteria.getRussianExamScore(), 100);

    }

    @Test
    public void testGetMathExamScore() {
        Assert.assertEquals(userCriteria.getMathExamScore(), 100);

    }

    @Test
    public void testGetPhysicsExamScore() {
        Assert.assertEquals(userCriteria.getPhysicsExamScore(), 100);

    }

    @Test
    public void testGetFacultyId() {
        Assert.assertEquals(userCriteria.getFacultyId(), 1);

    }

    @Test
    public void testGetId() {
        Assert.assertEquals(userCriteria.getId(), 99);

    }

    @Test
    public void testGetSumExams() {
        Assert.assertEquals(userCriteria.getSumExams(), 400);

    }
}