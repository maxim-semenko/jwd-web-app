package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.RequestContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomRequestContext implements RequestContext {

    private final HttpServletRequest httpServletRequest;

    public CustomRequestContext(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public List<String> getParamList() {
        return httpServletRequest.getParameterMap()
                .values()
                .stream()
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void setAttribute(String name, Object attr) {
        httpServletRequest.setAttribute(name, attr);
    }

    @Override
    public HttpSession getHttpSession() {
        return httpServletRequest.getSession();
    }

}
