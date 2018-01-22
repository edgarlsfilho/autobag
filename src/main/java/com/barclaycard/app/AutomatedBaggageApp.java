package com.barclaycard.app;

import com.barclaycard.model.Bag;
import com.barclaycard.model.Conveyor;
import com.barclaycard.model.Departure;
import com.barclaycard.repository.AutomatedBaggageDAO;
import com.barclaycard.repository.AutomatedBaggageFlatFileImpl;
import com.barclaycard.routing.BaggageRouterDijkstraImpl;

import java.util.Map;

public class AutomatedBaggageApp {

    public static void main(String args[]) {
        if(args.length == 0) {
            System.out.println("Usage: java com.studyguide.barclaycard.AutomateBaggageApp <filename>");
        }
        
        AutomatedBaggageDAO dao = new AutomatedBaggageFlatFileImpl(args[0]);

        Map<String, Departure> departures = dao.getDepartures();
        ConveyorSystem conveyorSystem = dao.getConvoyerSystem();
        conveyorSystem.setRouter(new BaggageRouterDijkstraImpl());

        for(Bag bag : dao.getBags()) {
            Conveyor src = conveyorSystem.findConveyor(bag.getEntryPoint());
            Conveyor dst;

            if(!"BaggageClaim".equals(bag.getFlightId())) {
                dst = conveyorSystem.findConveyor(departures.get(bag.getFlightId()).getFlightGate());
            } else {
                dst = conveyorSystem.findConveyor("BaggageClaim");
            }

            System.out.println(String.format("%s %s",bag.getBagNumber(), conveyorSystem.traceRoute(src, dst)));
        }
    }
}
