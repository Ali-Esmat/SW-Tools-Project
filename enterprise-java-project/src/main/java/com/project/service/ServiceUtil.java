package com.project.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.project.service.response.ErrorResponse;

public class ServiceUtil {

    public static int getIdFromContext(SecurityContext context) {
        return Integer.parseInt(context.getUserPrincipal().getName());
    }

    public static Response createErrorResponse(String message) {
        return Response.status(400).entity(new ErrorResponse(message)).build();
    }
}
