package com.project.service.response;

public class ContextResponse {
    private UserContextResponse context;

    public ContextResponse(UserContextResponse context) {
        this.context = context;
    }

    public UserContextResponse getContext() {
        return context;
    }

    public void setContext(UserContextResponse context) {
        this.context = context;
    }
}
