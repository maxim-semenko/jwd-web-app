package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.PathToPages;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandFactory;
import com.epam.jwd.controller.command.ResponseContext;
import junit.framework.TestCase;
import org.junit.Assert;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public class UserEditPageCommandTest extends TestCase {

    public void testExecute() {
        HttpServletRequest req = mock( HttpServletRequest.class);
        final Command command = CommandFactory.getCommand("USER_EDIT_PAGE");
        final ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        Assert.assertEquals(responseContext.getPage(), PathToPages.USER_EDIT_PAGE);
    }
}