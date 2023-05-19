package com.project.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RestaurantOwnerService {
    @PersistenceContext
    private EntityManager em;

    // Create restaurant menu
    // Edit restaurant: change menu meals for each restaurant
    // Get restaurant details by id
    // Create restaurant report: given a restaurant id print
    // how much the restaurant earns (summation of total amount of all completed
    // orders) , Number of completed orders, Number of canceled orders
    
}

package com.project.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionManagement;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.Optional;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.model.Restaurant;

@RequestScoped
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantOwneServices {
	
	@Inject
    private UserTransaction tx;
	
	@PersistenceContext(unitName="project")
	private EntityManager em;
	
	@POST
	@Path("CreateMenu")
	public void createMenu(Restaurant restaurant) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		tx.begin();
	    em.persist(restaurant);
        tx.commit();
        
	}
	
	@PUT
	@Path("EditMenu")
	public void editMenu(Restaurant restaurant) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		tx.begin();
	    em.persist(restaurant);
        tx.commit();
        
	}
	
	@GET
	@Path("GetRestaurantDetails")
	// If a Restaurant with the requested primary key is not found in the database, then the getRestaurantDetails() method returns null.
	public Optional<Restaurant> getRestaurantDetails(int id) {
		/*
		Restaurant restaurant = em.find(Restaurant.class, id);
        return restaurant != null ? Optional.of(restaurant) : Optional.empty();
		*/
		Restaurant restaurant = em.createQuery("SELECT r FROM Restaurant r WHERE r.id = :id", Restaurant.class).setParameter("id", id).getSingleResult();
		return restaurant != null ? Optional.of(restaurant) : Optional.empty();
		
	}

	@GET
	@Path("CreateRestaurantReport")
	// If a Restaurant with the requested primary key is not found in the database, then the getRestaurantDetails() method returns null.
	public void createRestaurantReport(int id) {
		
		
	}
	
}
