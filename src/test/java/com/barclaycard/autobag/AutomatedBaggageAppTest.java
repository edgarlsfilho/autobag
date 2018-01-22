package com.barclaycard.autobag;

import com.barclaycard.app.ConveyorSystem;
import com.barclaycard.model.Conveyor;
import com.barclaycard.model.ConveyorRoute;
import com.barclaycard.routing.BaggageRouterDijkstraImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AutomatedBaggageAppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AutomatedBaggageAppTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AutomatedBaggageAppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        Conveyor cat = new Conveyor("Concourse_A_Ticketing");
        Conveyor bcl = new Conveyor("BaggageClaim");

        Conveyor a01 = new Conveyor("A1");
        Conveyor a02 = new Conveyor("A2");
        Conveyor a03 = new Conveyor("A3");
        Conveyor a04 = new Conveyor("A4");
        Conveyor a05 = new Conveyor("A5");
        Conveyor a06 = new Conveyor("A6");
        Conveyor a07 = new Conveyor("A7");
        Conveyor a08 = new Conveyor("A8");
        Conveyor a09 = new Conveyor("A8");
        Conveyor a10 = new Conveyor("A10");

        cat.addRoute(new ConveyorRoute(a05,5));
        bcl.addRoute(new ConveyorRoute(a05,5));

        a01.addRoute(new ConveyorRoute(a02,1));
        a01.addRoute(new ConveyorRoute(a05,6));

        a02.addRoute(new ConveyorRoute(a03,1));
        a02.addRoute(new ConveyorRoute(a01,1));

        a03.addRoute(new ConveyorRoute(a02,1));
        a03.addRoute(new ConveyorRoute(a04,1));

        a04.addRoute(new ConveyorRoute(a03,1));

        a05.addRoute(new ConveyorRoute(a01,6));
        a05.addRoute(new ConveyorRoute(a10,4));
        a05.addRoute(new ConveyorRoute(bcl,5));
        a05.addRoute(new ConveyorRoute(cat,5));

        a06.addRoute(new ConveyorRoute(a07,1));

        a07.addRoute(new ConveyorRoute(a06,1));
        a07.addRoute(new ConveyorRoute(a08,1));

        a08.addRoute(new ConveyorRoute(a07,1));
        a08.addRoute(new ConveyorRoute(a09,1));

        a09.addRoute(new ConveyorRoute(a08,1));
        a09.addRoute(new ConveyorRoute(a10,1));

        a10.addRoute(new ConveyorRoute(a09,1));
        a10.addRoute(new ConveyorRoute(a05,4));

        ConveyorSystem g = new ConveyorSystem();
        g.setRouter(new BaggageRouterDijkstraImpl());

        g.addConveyor("Concourse_A_Ticketing", cat);
        g.addConveyor("BaggageClaim", bcl);
        g.addConveyor("A1", a01);
        g.addConveyor("A2", a02);
        g.addConveyor("A3", a03);
        g.addConveyor("A4", a04);
        g.addConveyor("A5", a05);
        g.addConveyor("A6", a06);
        g.addConveyor("A7", a07);
        g.addConveyor("A8", a08);
        g.addConveyor("A9", a09);
        g.addConveyor("A10", a10);

        /*
        System.out.println(g.traceRoute(cat, a01));
        System.out.println(g.traceRoute(a05, a04));
        System.out.println(g.traceRoute(a02, a01));
        System.out.println(g.traceRoute(a08, a05));
        System.out.println(g.traceRoute(a07, bcl));
        */

        assertEquals("Concourse_A_Ticketing A5 A1 : 11", g.traceRoute(cat, a01));
    }
}
