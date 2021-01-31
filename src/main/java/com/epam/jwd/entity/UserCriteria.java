package com.epam.jwd.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that helps to find {@link User} in database by criteria.
 */

@Builder
@ToString
@Getter
@Setter
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
}
