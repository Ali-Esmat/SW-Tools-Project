package com.project.service.response;

public class RunnerTripsResponse {
    private int noCompletedTrips;

    public RunnerTripsResponse(int noCompletedTrips) {
        this.noCompletedTrips = noCompletedTrips;
    }

    public int getNoCompletedTrips() {
        return noCompletedTrips;
    }

    public void setNoCompletedTrips(int noCompletedTrips) {
        this.noCompletedTrips = noCompletedTrips;
    }
}
