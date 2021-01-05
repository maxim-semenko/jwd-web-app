package com.epam.jwd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Entity {

    public static int COUNT_ID = 0;

    private String login;
    private String password;
    private String email;
    private EnumUserRole userRole;
    private EnumUserStatus userStatus;

    private String firstname;
    private String lastname;
    private int averageScore;
    private int russianExamScore;
    private int mathExamScore;
    private int physicsExamScore;
    private int facultyId;

    private int id;
    private int sumExams;

    public User(UserBuilder builder) {
        login = builder.getLogin();
        password = builder.getPassword();
        email = builder.getEmail();
        userRole = builder.getUserRole();
        firstname = builder.getFirstname();
        lastname = builder.getLastname();
        averageScore = builder.getAverageScore();
        firstname = builder.getFirstname();
        russianExamScore = builder.getRussianExamScore();
        mathExamScore = builder.getMathExamScore();
        physicsExamScore = builder.getPhysicsExamScore();
        facultyId = builder.getFacultyId();
        userStatus = builder.getUserStatus();
        id = ++COUNT_ID;
    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", averageScore=" + averageScore +
                ", russianExamScore=" + russianExamScore +
                ", mathExamScore=" + mathExamScore +
                ", physicsExamScore=" + physicsExamScore +
                ", facultyId=" + facultyId +
                ", id=" + id +
                ", sumExams=" + sumExams +
                '}';
    }
}
