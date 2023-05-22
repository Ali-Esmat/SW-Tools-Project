package com.project.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.enums.RunnerStatusEnum;
import com.project.enums.OrderStatusEnum;
import com.project.model.Runner;
import com.project.model.Orders;

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

    public Runner getRunnerById(int runnerId){
        TypedQuery<Runner> runnerQuery = em.createQuery("Select r from Runner where r.id = ?1",Runner.class);
        runnerQuery.setParameter(1, runnerId);
        return RepoUtil.getFirstResultOrNull(runnerQuery);
    }
    
    public int getCompletedTrips(int runnerId) {
        Runner runner = getRunnerById(runnerId);
        Set<Orders> orders = runner.getOrders();
        int totalNumberOfCompletedTrips = 0;
        for(Orders order : orders) {
        	if(order.getStatus() == OrderStatusEnum.DELIVERED)
        		totalNumberOfCompletedTrips++;
        }
        return totalNumberOfCompletedTrips;
    }

    public Runner setRunnerStatus(int runnerId, RunnerStatusEnum status) {
        Runner runner = getRunnerById(runnerId);
        runner.setStatus(status);
        return runner;
    }
}
