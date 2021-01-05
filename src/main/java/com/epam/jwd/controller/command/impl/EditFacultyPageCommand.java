package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.FacultyService;

public class EditFacultyPageCommand implements Command {

    private static final ResponseContext EDIT_FACULTY_PAGE = () -> PathToPages.EDIT_FACULTY_PAGE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("allFaculties", FacultyService.getInstance().selectAll());
        return EDIT_FACULTY_PAGE;
    }
}
