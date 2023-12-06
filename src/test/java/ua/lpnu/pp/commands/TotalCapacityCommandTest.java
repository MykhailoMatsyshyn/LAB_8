package ua.lpnu.pp.commands;

import org.junit.Test;
import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.Assert.*;

public class TotalCapacityCommandTest {
    @Test
    public void testExecute_CalculatesTotalCapacityAndCargoCapacity() {
        Airline airline = new Airline();
        Aircraft aircraft1 = new Aircraft("Boeing", "747", "ABC123", 200, 150.5, 25.0, 5000.0);
        Aircraft aircraft2 = new Aircraft("Airbus", "A320", "DEF456", 180, 120.0, 20.0, 4000.0);
        airline.addAircraft(aircraft1);
        airline.addAircraft(aircraft2);

        TotalCapacityCommand totalCapacityCommand = new TotalCapacityCommand(airline);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Locale.setDefault(Locale.US);

        totalCapacityCommand.execute();

        String expectedOutput = String.format(Locale.US,
                "Загальна пасажиромісткість усіх літаків: %d%nЗагальна вантажопідйомність усіх літаків: %.1f%n",
                aircraft1.getCapacity() + aircraft2.getCapacity(),
                aircraft1.getCargoCapacity() + aircraft2.getCargoCapacity());

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testGetDescription_ReturnsCorrectDescription() {
        Airline airline = new Airline();
        TotalCapacityCommand totalCapacityCommand = new TotalCapacityCommand(airline);

        String expectedDescription = "Загальна місткість та вантажопідйомність";
        String actualDescription = totalCapacityCommand.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }
}