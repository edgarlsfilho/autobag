package com.barclaycard.routing;


import com.barclaycard.model.Conveyor;
import com.barclaycard.model.ConveyorRoute;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BaggageRouterDijkstraImpl implements BaggageRouter {


    public String shortestPath(Conveyor start, Conveyor end) {

        Queue<BagaggeRouterPriority> queue = new PriorityQueue<BagaggeRouterPriority>(new Comparator<BagaggeRouterPriority>() {
            public int compare(BagaggeRouterPriority v1, BagaggeRouterPriority v2) {
                return ((Integer.valueOf(v1.getAccumulatedTravelTime()).compareTo(v2.getAccumulatedTravelTime())));
            }
        });

        queue.add(new BagaggeRouterPriority(null, start, 0));

        BagaggeRouterPriority currBagaggeRouterPriority;

        while (!queue.isEmpty()) {
            currBagaggeRouterPriority = queue.poll();

            if (currBagaggeRouterPriority.getCurrentConveyor().getName().equals(end.getName())) {
                return printTrace(currBagaggeRouterPriority) + ": " + currBagaggeRouterPriority.getAccumulatedTravelTime();
            }

            if (currBagaggeRouterPriority.getCurrentConveyor().getRoutes() != null) {
                for (ConveyorRoute route : currBagaggeRouterPriority.getCurrentConveyor().getRoutes()) {
                    queue.add(new BagaggeRouterPriority(currBagaggeRouterPriority, route.getDst(), route.getTravelTime() + currBagaggeRouterPriority.getAccumulatedTravelTime()));
                }
            }
        }

        return ":";
    }


    private String printTrace(BagaggeRouterPriority bagaggeRouterPriority) {
        String trace = bagaggeRouterPriority.getCurrentConveyor().getName() + " ";

        if (bagaggeRouterPriority.getPrevBagaggeRouterPriority() != null) {
            return printTrace(bagaggeRouterPriority.getPrevBagaggeRouterPriority()) + trace;
        }

        return trace;
    }

}


