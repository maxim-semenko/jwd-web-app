package com.epam.jwd.controller.command;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RequestContext {

    List<String> getParamList();

    void setAttribute(String name, Object attr);

    HttpSession getHttpSession();

}


