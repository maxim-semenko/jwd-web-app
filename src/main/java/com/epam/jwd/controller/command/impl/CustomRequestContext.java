package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, String> getParamMap() {
        Map<String, String[]> stringMap = httpServletRequest.getParameterMap();
        Map<String, String> map = new HashMap<>();
        for (String parameterName : stringMap.keySet()) {
            String[] values = stringMap.get(parameterName);
            if (values != null && values.length > 0) {
                map.put(parameterName, values[0]);
            }
        }
        return map;
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
