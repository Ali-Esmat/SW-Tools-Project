package com.project.service;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.RoleEnum;
import com.project.repo.RunnerRepo;
import com.project.service.response.RunnerTripsResponse;
import com.project.service.util.ServiceUtil;

@RequestScoped
@Path("trips")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
public class TripsService {
    @Inject
    private RunnerRepo runnerRepo;

    @GET
    public RunnerTripsResponse getRunnerTrips(@Context SecurityContext context) {
        return new RunnerTripsResponse(runnerRepo.getDeliveredOrders(ServiceUtil.getIdFromContext(context)).size());
    }

}
