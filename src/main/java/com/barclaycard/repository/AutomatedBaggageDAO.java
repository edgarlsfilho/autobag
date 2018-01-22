package com.barclaycard.repository;

import com.barclaycard.app.ConveyorSystem;
import com.barclaycard.model.Bag;
import com.barclaycard.model.Departure;

import java.util.List;
import java.util.Map;

public interface AutomatedBaggageDAO {
    ConveyorSystem getConvoyerSystem();
    Map<String, Departure> getDepartures();
    List<Bag> getBags();
}
