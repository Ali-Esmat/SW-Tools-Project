package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.model.Role;

@Stateless
public class RoleRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    // Gets role object by using role name
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty())
            return null;
        return query.getSingleResult();
    }
    // Creates a new role in the database
    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        em.persist(role);
        return role;
    }
}
