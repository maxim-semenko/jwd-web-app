package com.epam.jwd.entity;

import lombok.Getter;

/**
 * User-builder to create {@link User}.
 * Pattern builder
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Getter
public class UserBuilder {
    private String firstname;
    private String lastname;
    private int averageScore;
    private int russianExamScore;
    private int mathExamScore;
    private int physicsExamScore;
    private int facultyId;
    private EnumUserRole userRole;
    private EnumUserStatus userStatus;
    private int id;

    private String login;
    private String password;
    private String email;

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder setAverageScore(int averageScore) {
        this.averageScore = averageScore;
        return this;
    }

    public UserBuilder setRussianExamScore(int russianExamScore) {
        this.russianExamScore = russianExamScore;
        return this;
    }

    public UserBuilder setMathExamScore(int mathExamScore) {
        this.mathExamScore = mathExamScore;
        return this;
    }

    public UserBuilder setPhysicsExamScore(int physicsExamScore) {
        this.physicsExamScore = physicsExamScore;
        return this;
    }

    public UserBuilder setFacultyId(int facultyId) {
        this.facultyId = facultyId;
        return this;
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUserStatus(EnumUserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public UserBuilder setUserRole(EnumUserRole userRole) {
        this.userRole = userRole;
        return this;
    }
}
