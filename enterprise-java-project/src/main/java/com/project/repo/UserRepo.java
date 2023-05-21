package com.project.repo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.enums.RoleEnum;
import com.project.enums.RunnerStatusEnum;
import com.project.model.RestaurantOwner;
import com.project.model.Role;
import com.project.model.Runner;
import com.project.model.User;
import com.project.service.request.RestaurantOwnerRequest;
import com.project.service.request.RunnerRequest;
import com.project.service.request.UserRequest;

@Stateless
public class UserRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    @Inject
    private RoleRepo roleRepo;

    private User createBareUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public User createRunner(RunnerRequest runnerRequest) {
        User user = createBareUser(runnerRequest);
        Role runnerRole = roleRepo.getRoleByName(RoleEnum.RUNNER.toString());
        user.setRole(runnerRole);
        em.persist(user);

        Runner runner = new Runner();
        runner.setDeliveryFees(runnerRequest.getDeliveryFees());
        runner.setUser(user);
        runner.setStatus(RunnerStatusEnum.AVAILABLE);
        em.persist(runner);

        user.setRunner(runner);
        return user;
    }

    public User createRestaurantOwner(RestaurantOwnerRequest ownerRequest) {
        User user = createBareUser(ownerRequest);
        Role ownerRole = roleRepo.getRoleByName(RoleEnum.RESTAURANT_OWNER.toString());
        user.setRole(ownerRole);
        em.persist(user);

        RestaurantOwner owner = new RestaurantOwner();
        owner.setUser(user);
        em.persist(owner);

        user.setRestaurantOwner(owner);
        return user;
    }

    public User getUserByNameAndPassword(String name, String password) {
        TypedQuery<User> query = em.createQuery(
                "select u from User u where u.name = :name and u.password = :password",
                User.class);
        query.setParameter("name", name);
        query.setParameter("password", password);
        return RepoUtil.getFirstResultOrNull(query);
    }

    public User getUserByName(String name) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.name = :name", User.class);
        query.setParameter("name", name);
        return RepoUtil.getFirstResultOrNull(query);
    }
}
