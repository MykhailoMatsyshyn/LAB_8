package ua.lpnu.pp.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AirlineTest {
    private Airline airline;

    @Before
    public void setUp() {
        airline = new Airline();
    }

    @Test
    public void testAddAircraft() {
        Aircraft aircraft = new Aircraft("Boeing", "737", "12345", 150, 200.0, 15.0, 3000.0);

        airline.addAircraft(aircraft);
        int expectedSize = 1;
        int actualSize = airline.getAircrafts().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetAircrafts() {
        Aircraft aircraft1 = new Aircraft("Boeing", "737", "12345", 150, 200.0, 15.0, 3000.0);
        Aircraft aircraft2 = new Aircraft("Airbus", "A320", "54321", 200, 250.0, 14.5, 3500.0);

        airline.addAircraft(aircraft1);
        airline.addAircraft(aircraft2);

        List<Aircraft> aircrafts = airline.getAircrafts();
        int expectedSize = 2;
        int actualSize = aircrafts.size();

        assertEquals(expectedSize, actualSize);
        assertEquals(aircraft1, aircrafts.get(0));
        assertEquals(aircraft2, aircrafts.get(1));
    }

    @Test
    public void testFindAircraftsByFuelConsumption() {
        Aircraft aircraft1 = new Aircraft("Boeing", "737", "12345", 150, 200.0, 13.0, 3000.0);
        Aircraft aircraft2 = new Aircraft("Airbus", "A320", "54321", 200, 250.0, 14.5, 3500.0);
        Aircraft aircraft3 = new Aircraft("Embraer", "E190", "67890", 180, 220.0, 16.0, 3200.0);

        airline.addAircraft(aircraft1);
        airline.addAircraft(aircraft2);
        airline.addAircraft(aircraft3);

        List<Aircraft> foundAircrafts = airline.findAircraftsByFuelConsumption(14.0, 16.0);
        int expectedSize = 2;
        int actualSize = foundAircrafts.size();

        assertEquals(expectedSize, actualSize);
        assertEquals(aircraft2, foundAircrafts.get(0));
        assertEquals(aircraft3, foundAircrafts.get(1));
    }
}
