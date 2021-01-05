package com.epam.jwd.entity;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserCriteria extends Entity {

    private String login;
    private String password;
    private String email;
    private EnumUserRole userRole;

    private String firstname;
    private String lastname;
    private int averageScore;
    private int russianExamScore;
    private int mathExamScore;
    private int physicsExamScore;
    private int facultyId;

    private int id;
    private int sumExams;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public EnumUserRole getUserRole() {
        return userRole;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public int getRussianExamScore() {
        return russianExamScore;
    }

    public int getMathExamScore() {
        return mathExamScore;
    }

    public int getPhysicsExamScore() {
        return physicsExamScore;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public int getId() {
        return id;
    }

    public int getSumExams() {
        return sumExams;
    }
}
