package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.service.FacultyService;

/**
 * Class, which executes the command to edit {@link Faculty},
 * namely to change the number of seats on it.
 *
 * @version 0.0.1
 */

public class EditFacultyCommand implements Command {

    private static final ResponseContext EDIT_FACULTY_REDIRECT
            = new ResponseContextImpl(PathToPages.EDIT_FACULTY_REDIRECT, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(final RequestContext requestContext) {
        Faculty faculty = FacultyService.getInstance()
                .selectById(Integer.parseInt(requestContext.getParamMap().get("id")));
        faculty.setCountPlaces(Integer.parseInt(requestContext.getParamMap().get("countPlaces")));
        try {
            FacultyService.getInstance().update(faculty);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        return EDIT_FACULTY_REDIRECT;
    }
}
