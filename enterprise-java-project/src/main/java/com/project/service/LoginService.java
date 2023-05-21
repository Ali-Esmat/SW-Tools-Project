package com.project.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.model.User;
import com.project.repo.UserRepo;
import com.project.rest.request.LoginRequest;
import com.project.rest.response.LoginResponse;

@RequestScoped
@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
    @Inject
    private UserRepo userRepo;

    @POST
    public LoginResponse login(LoginRequest request) {
        User user = userRepo.getUserByNameAndPassword(request.getName(), request.getPassword());
        if (user == null) {
            throw new BadRequestException("Invalid name or password");
        }
        return new LoginResponse(user.getId() + ":" + user.getPassword());
    }
}
