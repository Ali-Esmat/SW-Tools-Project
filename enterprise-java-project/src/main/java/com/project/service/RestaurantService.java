package com.project.service;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.enums.OrderStatusEnum;
import com.project.enums.RoleEnum;
import com.project.model.Orders;
import com.project.model.Restaurant;
import com.project.repo.RestaurantRepo;
import com.project.service.request.RestaurantRequest;
import com.project.service.response.RestaurantReportResponse;
import com.project.service.response.RestaurantResponse;
import com.project.service.util.ServiceUtil;

@RequestScoped
@Path("restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(RoleEnum.Constants.RESTAURANT_OWNER_VALUE)
public class RestaurantService {
    @Inject
    private RestaurantRepo repo;

    @Inject
    private OrderService orderService;

    @POST
    public RestaurantResponse createRestaurant(@Context SecurityContext context, RestaurantRequest request) {
        Restaurant r = repo.createRestaurant(ServiceUtil.getIdFromContext(context), request.getName());
        return new RestaurantResponse(r);
    }

    private Restaurant restaurantOrFailIfNone(SecurityContext context) {
        Restaurant r = repo.getRestaurantForRestaurantOwner(ServiceUtil.getIdFromContext(context));
        if (r == null) {
            throw new BadRequestException(ServiceUtil.createErrorResponse("You don't have a restaurant yet"));
        }
        return r;
    }

    /*
     * The owner only has one restaurant, no need to specify the restaurant id as
     * mentioned in the specs
     * We can get the restaurant from the owner who sent the request
     */
    @GET
    public RestaurantResponse getRestaurantForRestaurantOwner(@Context SecurityContext context) {
        return new RestaurantResponse(restaurantOrFailIfNone(context));
    }


    @GET
    @Path("report")
    public RestaurantReportResponse getRestaurantReportForRestaurantOwner(@Context SecurityContext context) {
        Restaurant restaurant = restaurantOrFailIfNone(context);
        Set<Orders> orders = restaurant.getOrders();
        double gains = 0;
        int noCancelled = 0;
        int noCompleted = 0;
        if (orders != null) {
            for (Orders order : orders) {
                if (order.status == OrderStatusEnum.DELIVERED) {
                    gains += orderService.calculateOrderPrice(order);
                    ++noCompleted;
                } else if (order.status == OrderStatusEnum.CANCELED) {
                    ++noCancelled;
                }
            }
        }
        return new RestaurantReportResponse(restaurant.getId(), gains, noCompleted, noCancelled);
    }

    @RolesAllowed(RoleEnum.Constants.CUSTOMER_VALUE)
    @GET
    @Path("all")
    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = repo.getAllRestaurants();
        return ServiceUtil.entitiesToResponses(restaurants, (r) -> new RestaurantResponse(r));
    }
}
