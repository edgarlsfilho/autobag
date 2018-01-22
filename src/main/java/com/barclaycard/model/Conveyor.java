package com.barclaycard.model;

import java.util.ArrayList;
import java.util.List;

public class Conveyor {
    private String name;
    private List<ConveyorRoute> routes = new ArrayList<>(10);

    public Conveyor(String name) {
        this.name = name;
    }

    public void addRoute(ConveyorRoute conveyorRoute) {
        this.routes.add(conveyorRoute);
    }

    public String getName() {
        return name;
    }

    public List<ConveyorRoute> getRoutes() {
        return this.routes;
    }
}
