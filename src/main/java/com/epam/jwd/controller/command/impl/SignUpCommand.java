package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.EnumUserRole;
import com.epam.jwd.entity.EnumUserStatus;
import com.epam.jwd.entity.User;
import com.epam.jwd.entity.UserBuilder;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public final class SignUpCommand implements Command {

    private static final ResponseContext SIGNUP_PAGE = () -> PathToPages.SIGNUP_PAGE;
    private static final ResponseContext AFTER_SIGNUP_REDIRECT = () -> PathToPages.AFTER_SIGNUP_REDIRECT;

    /**
     * Method receives {@link RequestContext} requestContext and checks
     * if there is such {@link User} a user. If not, then it tries
     * to create a new user and insert it into the database.
     *
     * @param requestContext paramList from page
     * @return {@link ResponseContext} page
     */
    @Override
    public ResponseContext execute(final RequestContext requestContext) {
        requestContext.setAttribute("checkExistUser", false);
        requestContext.setAttribute("checkError", false);
        UserService userService = UserService.getInstance();

        List<String> paramList = requestContext.getParamList();
        final int countParam = 11;

        if (paramList.size() == countParam) {
            User user = createUser(paramList);
            Optional<User> optionalUser =
                    userService.getByCriteria(UserCriteria.builder().login(user.getLogin()).build());

            if (optionalUser.isPresent()) {
                requestContext.setAttribute("checkExistUser", true);
            } else {
                try {
                    userService.insert(user);
                    return AFTER_SIGNUP_REDIRECT;
                } catch (ValidatorException e) {
                    requestContext.setAttribute("checkError", true);
                    log.error("Can't register user " + e);
                }
            }
        }
        return SIGNUP_PAGE;
    }

    /**
     * Method that takes parameters from {@link RequestContext} - requestContext,
     * builds {@link User} user and return.
     *
     * @param paramList - requestContext from page
     * @return {@link User} user
     */
    private User createUser(final List<String> paramList) {
        return new User(new UserBuilder()
                .setFirstname(paramList.get(1))
                .setLastname(paramList.get(2))
                .setLogin(paramList.get(3))
                .setPassword(paramList.get(4))
                .setEmail(paramList.get(5))
                .setAverageScore(Integer.parseInt(paramList.get(6)))
                .setRussianExamScore(Integer.parseInt(paramList.get(7)))
                .setMathExamScore(Integer.parseInt(paramList.get(8)))
                .setPhysicsExamScore(Integer.parseInt(paramList.get(9)))
                .setFacultyId(Integer.parseInt(paramList.get(10)))
                .setUserStatus(EnumUserStatus.UNKNOWN)
                .setUserRole(EnumUserRole.CLIENT));
    }

}
