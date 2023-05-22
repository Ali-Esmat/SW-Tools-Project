package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.model.Customer;
import com.project.model.User;

@Stateless
public class CustomerRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    public Customer getCustomerById(int userId) {
        TypedQuery<User> customerQuery = em.createQuery("Select m from User m where m.id = ?1 ", User.class);
        customerQuery.setParameter(1, userId);
        User user = customerQuery.getSingleResult();
        return user.getCustomer();

    }
}
