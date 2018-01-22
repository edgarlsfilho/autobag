package com.barclaycard.routing;

import com.barclaycard.model.Conveyor;

public class BagaggeRouterPriority {
    private BagaggeRouterPriority prevBagaggeRouterPriority;
    private Conveyor currentConveyor;
    private int accumulatedTravelTime;

    public BagaggeRouterPriority(BagaggeRouterPriority prevBagaggeRouterPriority, Conveyor currentConveyor, int accumulatedTravelTime) {
        this.prevBagaggeRouterPriority = prevBagaggeRouterPriority;
        this.currentConveyor = currentConveyor;
        this.accumulatedTravelTime = accumulatedTravelTime;
    }

    public BagaggeRouterPriority getPrevBagaggeRouterPriority() {
        return prevBagaggeRouterPriority;
    }

    public Conveyor getCurrentConveyor() {
        return currentConveyor;
    }

    public int getAccumulatedTravelTime() {
        return accumulatedTravelTime;
    }
}
