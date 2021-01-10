package com.epam.jwd.controller.command;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RequestContext {

    List<String> getParamList();

    Map<String, String> getParamMap();

    void setAttribute(String name, Object attr);

    HttpSession getHttpSession();

}


