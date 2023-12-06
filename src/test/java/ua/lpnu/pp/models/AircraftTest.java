package ua.lpnu.pp.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class AircraftTest {
    @Test
    public void testAircraftConstructor() {
        Aircraft aircraft = new Aircraft("Boeing", "737", "12345", 150, 200.0, 15.0, 3000.0);

        assertEquals("Boeing", aircraft.getManufacturer());
        assertEquals("737", aircraft.getModel());
        assertEquals("12345", aircraft.getSerialNumber());
        assertEquals(150, aircraft.getCapacity());
        assertEquals(200.0, aircraft.getCargoCapacity(), 0.001);
        assertEquals(15.0, aircraft.getFuelConsumption(), 0.001);
        assertEquals(3000.0, aircraft.getFlightRange(), 0.001);
    }

    @Test
    public void testNoArgsConstructor() {
        Aircraft aircraft = new Aircraft();

        assertNotNull(aircraft);

        assertNull(aircraft.getManufacturer());
        assertNull(aircraft.getModel());
        assertNull(aircraft.getSerialNumber());
        assertEquals(0, aircraft.getCapacity());
        assertEquals(0.0, aircraft.getCargoCapacity(), 0.001);
        assertEquals(0.0, aircraft.getFuelConsumption(), 0.001);
        assertEquals(0.0, aircraft.getFlightRange(), 0.001);
    }

    @Test
    public void testAircraftSettersAndGetters() {
        Aircraft aircraft = new Aircraft();
        aircraft.setManufacturer("Airbus");
        aircraft.setModel("A320");
        aircraft.setSerialNumber("54321");
        aircraft.setCapacity(200);
        aircraft.setCargoCapacity(250.0);
        aircraft.setFuelConsumption(14.5);
        aircraft.setFlightRange(3500.0);

        assertEquals("Airbus", aircraft.getManufacturer());
        assertEquals("A320", aircraft.getModel());
        assertEquals("54321", aircraft.getSerialNumber());
        assertEquals(200, aircraft.getCapacity());
        assertEquals(250.0, aircraft.getCargoCapacity(), 0.001);
        assertEquals(14.5, aircraft.getFuelConsumption(), 0.001);
        assertEquals(3500.0, aircraft.getFlightRange(), 0.001);
    }
}