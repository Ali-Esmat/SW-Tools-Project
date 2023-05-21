package com.project.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.repo.UserRepo;
import com.project.service.request.RunnerRequest;

@RequestScoped
@Path("signup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignupService {
    @Inject
    private UserRepo userRepo;

    @POST
    @Path("runner")
    public void signupRunner(RunnerRequest runnerRequest) {
        userRepo.createRunner(runnerRequest);
    }
}
