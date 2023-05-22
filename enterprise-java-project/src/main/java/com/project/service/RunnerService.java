package com.project.service;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.project.model.Runner;
import com.project.model.Orders;
import com.project.enums.OrderStatusEnum;
import com.project.enums.RoleEnum;
import com.project.enums.RunnerStatusEnum;
import com.project.repo.RunnerRepo;
import com.project.repo.OrderRepo;
import com.project.service.util.ServiceUtil;


@RequestScoped
@Path("runner")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RunnerService {
	@Inject
    private RunnerRepo runnerRepo;
	
	@Inject
    private OrderRepo orderRepo;
	
	@PUT
	@RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
	public String changeOrderStatusToDelivered(int orderId) {
		Orders order = orderRepo.setOrderStatus(orderId, OrderStatusEnum.DELIVERED);
        return "The order with id " + order.getId() + " is " + order.getStatus();
    }
	
	@PUT
	@RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
	public String changeRunnerStatusToAvailable(@Context SecurityContext context) {
		Runner runner = runnerRepo.setRunnerStatus(ServiceUtil.getIdFromContext(context), RunnerStatusEnum.AVAILABLE);
        return "Hello " + runner.getUser().getName() + " You are now " + runner.getStatus();
    }
	
	@GET
	@RolesAllowed(RoleEnum.Constants.RUNNER_VALUE)
	public int getRunnerTrips(@Context SecurityContext context) {
		int totalNumberOfCompletedTrips = runnerRepo.getCompletedTrips(ServiceUtil.getIdFromContext(context));
        return totalNumberOfCompletedTrips;
    }

}
