package com.project.service;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.RoleEnum;
import com.project.model.Meal;
import com.project.model.Restaurant;
import com.project.repo.MealRepo;
import com.project.repo.RestaurantRepo;
import com.project.service.request.MealRequest;
import com.project.service.response.MealResponse;
import com.project.service.util.ServiceUtil;

@RequestScoped
@Path("restaurant/meal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(RoleEnum.Constants.RESTAURANT_OWNER_VALUE)
public class MealService {
    @Inject
    private RestaurantRepo restaurantRepo;

    @Inject
    private MealRepo mealRepo;

    @POST
    public MealResponse createMeal(@Context SecurityContext context, MealRequest request) {
        Restaurant restaurant = restaurantRepo
                .getRestaurantForRestaurantOwner(ServiceUtil.getIdFromContext(context));
        if (restaurant == null)
            throw new BadRequestException(ServiceUtil.createErrorResponse("You do not have any restaurants"));
        Meal meal = mealRepo.createMealForRestaurant(restaurant, request.getName(), request.getPrice());
        return new MealResponse(meal);
    }

    private Meal mealOrFailIfDoesNotOwnMeal(SecurityContext context, int id) {
        Restaurant restaurant = restaurantRepo
                .getRestaurantForRestaurantOwner(ServiceUtil.getIdFromContext(context));
        Meal meal = mealRepo.getMealById(id);
        if (meal == null || meal.getRestaurant().getId() != restaurant.getId())
            throw new BadRequestException(ServiceUtil.createErrorResponse("Can't find the specified meal"));
        return meal;
    }

    @PUT
    @Path("{id}")
    public MealResponse editMeal(@Context SecurityContext context, @PathParam("id") int id, MealRequest request) {
        Meal meal = mealOrFailIfDoesNotOwnMeal(context, id);
        mealRepo.editMeal(meal, request.getName(), request.getPrice());
        return new MealResponse(meal);
    }

    @DELETE
    @Path("{id}")
    public void removeMeal(@Context SecurityContext context, @PathParam("id") int id) {
        Meal meal = mealOrFailIfDoesNotOwnMeal(context, id);
        mealRepo.deleteMeal(meal);
    }
}
