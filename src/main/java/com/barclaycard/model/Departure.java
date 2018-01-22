package com.barclaycard.model;

public class Departure {

    private String flightId;
    private String flightGate;
    private String destination;
    private String travelTime;

    public Departure(String flightId, String flightGate, String destination, String travelTime) {
        this.flightId = flightId;
        this.flightGate = flightGate;
        this.destination = destination;
        this.travelTime = travelTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getFlightGate() {
        return flightGate;
    }

    public String getDestination() {
        return destination;
    }

    public String getTravelTime() {
        return travelTime;
    }
}
