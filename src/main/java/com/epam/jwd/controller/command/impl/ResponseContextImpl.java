package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.ResponseContext;

/**
 * Class that store path for jsp page and responseType.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class ResponseContextImpl implements ResponseContext {
    String page;
    ResponseType responseType;

    public ResponseContextImpl(String page, ResponseType responseType) {
        this.page = page;
        this.responseType = responseType;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }
}
