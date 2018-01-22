package com.barclaycard.routing;

import com.barclaycard.model.Conveyor;

public interface BaggageRouter {
    String shortestPath(Conveyor start, Conveyor end);
}
