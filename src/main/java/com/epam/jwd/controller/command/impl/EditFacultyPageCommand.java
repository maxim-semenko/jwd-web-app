package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.RequestContext;
import com.epam.jwd.controller.command.ResponseContext;
import com.epam.jwd.service.FacultyService;

/**
 * Class command that show page with edit faculties.
 *
 * @version 0.0.1
 */

public class EditFacultyPageCommand implements Command {

    private static final ResponseContext EDIT_FACULTY_PAGE
            = new ResponseContextImpl(PathToPages.EDIT_FACULTY_PAGE, ResponseContext.ResponseType.FORWARD);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("allFaculties", FacultyService.getInstance().selectAll());
        return EDIT_FACULTY_PAGE;
    }
}
