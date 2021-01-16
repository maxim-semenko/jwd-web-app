package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.ResponseContext;

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
