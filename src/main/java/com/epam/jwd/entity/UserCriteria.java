package com.epam.jwd.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Class that helps to find {@link User} in database by criteria.
 */

@Builder
@ToString
@Getter
public class UserCriteria extends Entity {

    private final String login;
    private final String password;
    private final String email;
    private final EnumUserRole userRole;

    private final String firstname;
    private final String lastname;
    private final int averageScore;
    private final int russianExamScore;
    private final int mathExamScore;
    private final int physicsExamScore;
    private final int facultyId;

    private final int id;
    private final int sumExams;
}
