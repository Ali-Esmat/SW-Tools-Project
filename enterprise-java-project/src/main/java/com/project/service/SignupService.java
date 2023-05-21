package com.project.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.repo.UserRepo;
import com.project.service.request.CustomerRequest;
import com.project.service.request.RestaurantOwnerRequest;
import com.project.service.request.RunnerRequest;

@RequestScoped
@Path("signup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignupService {
    @Inject
    private UserRepo userRepo;

    private void failIfUserWithSameNameExists(String name) {
        if (userRepo.getUserByName(name) != null)
            throw new BadRequestException("User already exists");
    }

    @POST
    @Path("runner")
    public void signupRunner(RunnerRequest runnerRequest) {
        failIfUserWithSameNameExists(runnerRequest.getName());
        userRepo.createRunner(runnerRequest);
    }

    @POST
    @Path("owner")
    public void signUpRestaurantOwner(RestaurantOwnerRequest ownerRequest) {
        failIfUserWithSameNameExists(ownerRequest.getName());
        userRepo.createRestaurantOwner(ownerRequest);
    }

    @POST
    @Path("customer")
    public void signupCustomer(CustomerRequest customerRequest) {
        failIfUserWithSameNameExists(customerRequest.getName());
        userRepo.createCustomer(customerRequest);
    }
}
