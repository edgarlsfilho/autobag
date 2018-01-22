package com.barclaycard.repository;

import com.barclaycard.model.Conveyor;
import com.barclaycard.model.ConveyorRoute;
import com.barclaycard.app.ConveyorSystem;
import com.barclaycard.model.Bag;
import com.barclaycard.model.Departure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomatedBaggageFlatFileImpl implements AutomatedBaggageDAO {
    private final String CONVEYOR_SECTION = "# Section: Conveyor System";
    private final String DEPARTURES_SECTION = "# Section: Departures";
    private final String BAGS_SECTION = "# Section: Bags";

    private String currSection = CONVEYOR_SECTION;
    private String fileName;

    private ConveyorSystem conveyorSystem =  new ConveyorSystem();
    private Map<String, Departure> departures = new HashMap<>();
    private List<Bag> bags = new ArrayList<>(10);

    public AutomatedBaggageFlatFileImpl(String fileName) {
        this.fileName = fileName;
        this.load();
    }

    private void load() {
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(this.fileName));
            String currentLine;

            while ((currentLine = inFile.readLine()) != null && !currentLine.equals("")) {
                if(currentLine.contains("Section")) {
                    setSection(currentLine);
                } else {
                    processRecord(currentLine);
                }
            }

            inFile.close();

        } catch (IOException ex) {
            System.exit(1);
            System.out.println(String.format("File not found - %s", this.fileName));
        }
    }

    private void setSection(String section) {
        if(CONVEYOR_SECTION.equals(section)) {
            currSection = CONVEYOR_SECTION;
        } else if(DEPARTURES_SECTION.equals(section)) {
            currSection = DEPARTURES_SECTION;
        } else if(BAGS_SECTION.equals(section)) {
            currSection = BAGS_SECTION;
        }
    }

    private void processRecord(String record) {
        if(CONVEYOR_SECTION.equals(currSection)) {
            processConvoyer(record);
        } else if(DEPARTURES_SECTION.equals(currSection)) {
            processDeparture(record);
        } else if(BAGS_SECTION.equals(currSection)) {
            processBag(record);
        }
    }

    private void processConvoyer(String record) {
        String fields[] = record.split(" ");

        Conveyor src = conveyorSystem.findConveyor(fields[0]);
        Conveyor dst = conveyorSystem.findConveyor(fields[1]);

        if(src == null) {
            src = new Conveyor(fields[0]);
        }

        if(dst == null) {
            dst = new Conveyor(fields[1]);
        }

        src.addRoute(new ConveyorRoute(dst,Integer.parseInt(fields[2])));
        dst.addRoute(new ConveyorRoute(src,Integer.parseInt(fields[2])));

        conveyorSystem.addConveyor(fields[0], src);
        conveyorSystem.addConveyor(fields[1], dst);
    }

    private void processDeparture(String record) {
        String fields[] = record.split(" ");
        departures.put(fields[0], new Departure(fields[0], fields[1], fields[2], fields[3]));
    }

    private void processBag(String record) {
        String fields[] = record.split(" ");

        if("ARRIVAL".equals(fields[2])) {
            fields[2] = "BaggageClaim";
        }

        bags.add(new Bag(fields[0], fields[1], fields[2]));
    }

    @Override
    public ConveyorSystem getConvoyerSystem() {
        return conveyorSystem;
    }

    @Override
    public Map<String, Departure> getDepartures() {
        return departures;
    }

    @Override
    public List<Bag> getBags() {
        return bags;
    }
}
