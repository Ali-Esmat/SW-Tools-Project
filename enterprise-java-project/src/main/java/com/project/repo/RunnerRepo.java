package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.enums.RunnerStatusEnum;
import com.project.model.Runner;

@Stateless
public class RunnerRepo {
    @PersistenceContext(unitName = "project")
    private EntityManager em;

    public Runner getFirstFreeRunner() {
        TypedQuery<Runner> runnerQuery = em.createQuery("SELECT r FROM Runner r WHERE r.status = :status",
                Runner.class);
        runnerQuery.setParameter("status", RunnerStatusEnum.AVAILABLE);
        return RepoUtil.getFirstResultOrNull(runnerQuery);
    }
}
