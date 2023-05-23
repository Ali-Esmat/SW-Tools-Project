package com.project.service;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.model.User;
import com.project.repo.UserRepo;
import com.project.service.response.ContextResponse;
import com.project.service.response.UserContextResponse;
import com.project.service.util.ServiceUtil;

@RequestScoped
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @Inject
    UserRepo repo;

    @GET
    public ContextResponse getUserContext(@Context SecurityContext context) {
        Principal p = context.getUserPrincipal();
        if (p == null) {
            return new ContextResponse(null);
        }
        int userId = ServiceUtil.getIdFromContext(context);
        User user = repo.getUserById(userId);
        if (user == null) {
            return new ContextResponse(null);
        }
        UserContextResponse cr = new UserContextResponse(userId, user.getName(), user.getRole().getName());
        return new ContextResponse(cr);
    }
}
