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
import com.epam.jwd.service.PasswordSecurityService;
import com.epam.jwd.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Log4j2
public class AcceptUserEditCommand implements Command {

    private static final ResponseContext USER_CABINET_REDIRECT = () -> PathToPages.USER_CABINET_REDIRECT;
    private static final ResponseContext HOME_REDIRECT = () -> PathToPages.HOME_REDIRECT;
    private static final ResponseContext USER_EDIT_PAGE = () -> PathToPages.USER_EDIT_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession();
        if (session.getAttribute("User") == null) {
            session.setAttribute("isLogout", true);
            return HOME_REDIRECT;
        }

        User oldUser = (User) session.getAttribute("User");
        User newUser = createUser(requestContext.getParamList());
        newUser.setUserStatus(oldUser.getUserStatus());
        newUser.setId(oldUser.getId());

        if (checkExistLogin(newUser.getLogin(), oldUser.getLogin()).isPresent()) {
            requestContext.setAttribute("checkExistUser", true);
            return USER_EDIT_PAGE;
        }

        if (!requestContext.getParamList().get(4).equals(oldUser.getPassword())) {
            newUser.setPassword(PasswordSecurityService.getInstance().doHashing(requestContext.getParamList().get(4)));
        }
        try {
            UserService.getInstance().update(newUser);
            session.setAttribute("User", newUser);
        } catch (ValidatorException e) {
            log.error("Can't update user" + e);
        }
        return USER_CABINET_REDIRECT;
    }

    /**
     * Method that takes parameters from {@link RequestContext} - requestContext,  builds user and return.
     *
     * @param paramList - requestContext from page
     * @return {@link User} newUser
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
                .setUserRole(EnumUserRole.CLIENT));
    }

    public Optional<User> checkExistLogin(String newLogin, String oldLogin) {
        Optional<User> optional = Optional.empty();
        if (!newLogin.equals(oldLogin)) {
            UserCriteria userCriteria = UserCriteria.builder().login(newLogin).build();
            optional = UserService.getInstance().getByCriteria(userCriteria);
        }
        return optional;
    }
}
