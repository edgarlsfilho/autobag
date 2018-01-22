package com.barclaycard.model;

import com.barclaycard.model.Conveyor;

public class ConveyorRoute {
    private Conveyor dst;
    private int travelTime;

    public ConveyorRoute(Conveyor dst, int travelTime) {
        this.dst = dst;
        this.travelTime = travelTime;
    }

    public Conveyor getDst() {
        return dst;
    }

    public int getTravelTime() {
        return travelTime;
    }
}
