package com.project.service.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public static <T, E, K> List<T> entitiesToResponses(Collection<E> entities, EntityToResponseFunction<T, E> fn) {
        List<T> responses = new ArrayList<T>();
        if (entities != null)
            for (E entity : entities)
                responses.add(fn.execute(entity));
        return responses;
    }
}
