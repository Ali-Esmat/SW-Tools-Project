package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.enums.RunnerStatusEnum;
import com.project.model.Runner;
import com.project.model.User;

@Stateless
public class RunnerRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    public Runner getFirstFreeRunner() {
        TypedQuery<Runner> runnerQuery = em.createQuery("Select r from Runner r where r.status = :status",
                Runner.class);
        runnerQuery.setParameter("status", RunnerStatusEnum.AVAILABLE);
        return RepoUtil.getFirstResultOrNull(runnerQuery);
    }

    public Runner getRunnerById(int userId) {
        TypedQuery<User> userQuery = em.createQuery("select u from User u where u.id = :id", User.class);
        userQuery.setParameter("id", userId);
        User user = RepoUtil.getFirstResultOrNull(userQuery);
        if (user == null)
            return null;
        return user.getRunner();
    }

    public Runner setRunnerStatus(int runnerId, RunnerStatusEnum status) {
        Runner runner = getRunnerById(runnerId);
        runner.setStatus(status);
        return runner;
    }
}
