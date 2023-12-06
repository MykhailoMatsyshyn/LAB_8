package ua.lpnu.pp.commands;

import org.junit.Test;
import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class DeletePlaneCommandTest {
    @Test
    public void testExecute_RemovesExistingPlaneFromAirline() {
        Airline airline = new Airline();
        Aircraft aircraft1 = new Aircraft("Boeing", "747", "ABC123", 200, 150.5, 25.0, 5000.0);
        Aircraft aircraft2 = new Aircraft("Airbus", "A320", "DEF456", 180, 120.0, 20.0, 4000.0);
        airline.addAircraft(aircraft1);
        airline.addAircraft(aircraft2);

        String input = "ABC123\n";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeletePlaneCommand deletePlaneCommand = new DeletePlaneCommand(airline);

        deletePlaneCommand.execute();

        List<Aircraft> remainingAircrafts = airline.getAircrafts();
        assertEquals(1, remainingAircrafts.size());
        assertFalse(remainingAircrafts.contains(aircraft1));
        assertTrue(remainingAircrafts.contains(aircraft2));

        System.setIn(inputStream);
    }

    @Test
    public void testExecute_NoSuchPlaneInAirline() {
        Airline airline = new Airline();
        Aircraft aircraft1 = new Aircraft("Boeing", "747", "ABC123", 200, 150.5, 25.0, 5000.0);
        airline.addAircraft(aircraft1);

        String input = "DEF456\n";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeletePlaneCommand deletePlaneCommand = new DeletePlaneCommand(airline);

        deletePlaneCommand.execute();

        List<Aircraft> remainingAircrafts = airline.getAircrafts();
        assertEquals(1, remainingAircrafts.size());
        assertTrue(remainingAircrafts.contains(aircraft1));

        System.setIn(inputStream);
    }

    @Test
    public void testExecute_EmptyAirline() {
        // Arrange
        Airline airline = new Airline();

        String input = "ABC123\n";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeletePlaneCommand deletePlaneCommand = new DeletePlaneCommand(airline);

        // Act
        deletePlaneCommand.execute();

        // Assert
        List<Aircraft> remainingAircrafts = airline.getAircrafts();
        assertEquals(0, remainingAircrafts.size());

        System.setIn(inputStream);
    }

    @Test
    public void testGetDescription_ReturnsCorrectDescription() {
        Airline airline = new Airline();
        DeletePlaneCommand deletePlaneCommand = new DeletePlaneCommand(airline);

        String expectedDescription = "Видалити літак за серійним номером";
        String actualDescription = deletePlaneCommand.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }
}