package com.project.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionManagement;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.model.Meal;
import com.project.model.Restaurant;
import com.project.utility.RestaurantReport;

@RequestScoped
@TransactionManagement(TransactionManagementType.BEAN)
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantOwnerService {
	
	@Inject
    private UserTransaction tx;
	
	@PersistenceContext(unitName="project")
	private EntityManager em;

	// must set the owner here 
	// must persist every individual meal
	@POST
	@Path("CreateMenu")
	public void createMenu(Restaurant restaurant) {

		tx.begin();
		Set<Meal> meals = restaurant.getMeals();
		for (Meal meal: meals ){
			em.persist(meal);
		}
		
		em.persist(restaurant);
        tx.commit();
        
	}
	
	@PUT
	@Path("EditMenu")
	public void editMenu(Restaurant restaurant) {
		
		tx.begin();
		Set<Meal> meals = restaurant.getMeals();
		
		for (Meal meal: meals){
			em.persist(meal);
		}
		
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
	public RestaurantReport createRestaurantReport(int id) throws NullPointerException {
	
		Restaurant restaurant = em.createQuery("Select r FROM Restaurant r WHERE r.id =?1",Restaurant.class).setParameter("id", id).getSingleResult();
		if (restaurant == null){
			throw new NullPointerException("Restaurant doesn't exist");
		}
		return new RestaurantReport(restaurant);
	}
	
}
