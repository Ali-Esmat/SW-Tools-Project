package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.model.Customer;

@Stateless
public class CustomerRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    public Customer selectCustomerById(int id){

        TypedQuery<Customer> customerQuery = em.createQuery("SELECT m FROM Customer m where m.id = ?1 ", Customer.class);
        customerQuery.setParameter(1, id);
        return customerQuery.getSingleResult();

    }
}
