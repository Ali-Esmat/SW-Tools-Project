package com.project.service;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.RoleEnum;

@RequestScoped
@Path("health")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HealthService {
    @GET
    public String health() {
        return "Up and running";
    }

    @GET
    @Path("runner")
    @RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
    public String healthRunner(@Context SecurityContext context) {
        return "Hello runner, your id is " + context.getUserPrincipal().getName();
    }
}
