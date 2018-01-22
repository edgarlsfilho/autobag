package com.barclaycard.app;

import com.barclaycard.model.Conveyor;
import com.barclaycard.routing.BaggageRouter;

import java.util.HashMap;
import java.util.Map;

public class ConveyorSystem {
    private Map<String, Conveyor> conveyors = new HashMap<>();
    private BaggageRouter router;

    public void addConveyor(String name, Conveyor conveyor) {
        conveyors.put(name, conveyor);
    }

    public Conveyor findConveyor(String name) {
        return conveyors.get(name);
    }

    public String traceRoute(Conveyor src, Conveyor dst) {
        return router.shortestPath(src, dst);
    }

    public void setRouter(BaggageRouter router) {
        this.router =  router;
    }

}
