package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.UserCriteria;
import com.epam.jwd.service.UserService;

import java.util.Map;

public class FindUsersByCriteriaCommand implements Command {

    private static final ResponseContext FIND_USERS_BY_CRITERIA
            = new ResponseContextImpl(PathToPages.FIND_USERS_BY_CRITERIA, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("usersByCriteria",
                UserService.getInstance().getAllByCriteria(createCriteria(requestContext)));

        System.out.println(UserService.getInstance().getAllByCriteria(createCriteria(requestContext)));
        return FIND_USERS_BY_CRITERIA;
    }

    /**
     * Method that creates {@link UserCriteria} by params from {@link Map}.
     */
    private UserCriteria createCriteria(RequestContext requestContext) {
        Map<String, String> paramMap = requestContext.getParamMap();
        System.out.println(paramMap);
        UserCriteria userCriteria = UserCriteria.builder().build();

        if (!paramMap.get("userId").equals("")) {
            userCriteria.setId(Integer.parseInt(paramMap.get("userId")));
        }
        if (!paramMap.get("userLogin").equals("")) {
            userCriteria.setLogin(paramMap.get("userLogin"));
        }
        if (!paramMap.get("userFirstname").equals("")) {
            userCriteria.setFirstname(paramMap.get("userFirstname"));
        }
        if (!paramMap.get("userLastname").equals("")) {
            userCriteria.setLastname(paramMap.get("userLastname"));
        }
        if (!paramMap.get("userEmail").equals("")) {
            userCriteria.setEmail(paramMap.get("userEmail"));
        }
        if (!paramMap.get("userAverageScore").equals("")) {
            userCriteria.setAverageScore(Integer.parseInt(paramMap.get("userAverageScore")));
        }
        if (!paramMap.get("userRussianScore").equals("")) {
            userCriteria.setRussianExamScore(Integer.parseInt(paramMap.get("userRussianScore")));
        }
        if (!paramMap.get("userMathScore").equals("")) {
            userCriteria.setMathExamScore(Integer.parseInt(paramMap.get("userMathScore")));
        }
        if (!paramMap.get("userPhysicsScore").equals("")) {
            userCriteria.setPhysicsExamScore(Integer.parseInt(paramMap.get("userPhysicsScore")));
        }
        if (!paramMap.get("userFacultyId").equals("")) {
            userCriteria.setFacultyId(Integer.parseInt(paramMap.get("userFacultyId")));
        }
        return userCriteria;
    }

}
