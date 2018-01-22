package com.barclaycard.model;

public class Bag {

    private String bagNumber;
    private String entryPoint;
    private String flightId;

    public Bag(String bagNumber, String entryPoint, String flightId) {
        this.bagNumber = bagNumber;
        this.entryPoint = entryPoint;
        this.flightId = flightId;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public String getFlightId() {
        return flightId;
    }
}
