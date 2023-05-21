package com.project.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.RoleEnum;
import com.project.model.Restaurant;
import com.project.repo.RestaurantRepo;
import com.project.service.request.RestaurantRequest;
import com.project.service.response.RestaurantResponse;

@RequestScoped
@Path("restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(RoleEnum.Constants.RESTAURANT_OWNER_VALUE)
public class RestaurantService {
    @Inject
    private RestaurantRepo repo;

    @POST
    public Restaurant createRestaurant(@Context SecurityContext context, RestaurantRequest request) {
        return repo.createRestaurant(ServiceUtil.getIdFromContext(context), request.getName());
    }

    /*
     * The owner only has one restaurant, no need to specify the restaurant id as
     * mentioned in the specs
     * We can get the restaurant from the owner who sent the request
     */
    @GET
    public RestaurantResponse getRestaurantForRestaurantOwner(@Context SecurityContext context) {
        Restaurant r = repo.getRestaurantForRestaurantOwner(ServiceUtil.getIdFromContext(context));
        return new RestaurantResponse(r);
    }

    @RolesAllowed(RoleEnum.Constants.CUSTOMER_VALUE)
    @GET
    @Path("all")
    public List<Restaurant> getAllRestaurants() {
        return repo.getAllRestaurants();
    }
}
