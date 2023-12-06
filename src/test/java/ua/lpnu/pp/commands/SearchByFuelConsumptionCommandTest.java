package ua.lpnu.pp.commands;

import org.junit.After;
import org.junit.Test;
import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchByFuelConsumptionCommandTest {

    private final InputStream systemIn = System.in;
    private final Airline testAirline;

    public SearchByFuelConsumptionCommandTest() {
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(new Aircraft("Boeing", "747", "12345", 300, 4000.0, 20.5, 8000.0));
        aircraftList.add(new Aircraft("Airbus", "A320", "54321", 150, 2500.0, 15.0, 6000.0));
        testAirline = new Airline(aircraftList);
    }

    @After
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void testExecuteWithInput() {
        String inputData = "10.0\n20.0\n";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(inputStream);

        SearchByFuelConsumptionCommand searchCommand = new SearchByFuelConsumptionCommand(testAirline);

        searchCommand.execute();
    }

    @Test
    public void testExecuteNoPlanesFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String inputData = "30.0\n40.0\n";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));

        SearchByFuelConsumptionCommand searchCommand = new SearchByFuelConsumptionCommand(testAirline);
        searchCommand.execute();

        String expectedOutput = "Введіть мінімальне значення споживання пального:\r\n" +
                "» Введіть максимальне значення споживання пального:\r\n" +
                "» Літаків зі споживанням пального в заданому діапазоні не знайдено.\r\n";

        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testGetDescription() {
        Airline testAirline = new Airline();
        SearchByFuelConsumptionCommand searchCommand = new SearchByFuelConsumptionCommand(testAirline);
        String expectedDescription = "Пошук за параметрами споживання пального";

        assertEquals(expectedDescription, searchCommand.getDescription());
    }

    @Test
    public void testConstructor() {
        Airline testAirline = new Airline();
        SearchByFuelConsumptionCommand searchCommand = new SearchByFuelConsumptionCommand(testAirline);

        assertNotNull(searchCommand);
        assertEquals(testAirline, searchCommand.getAirline());
    }

    @Test
    public void testGetAirline() {
        Airline testAirline = new Airline();
        SearchByFuelConsumptionCommand searchCommand = new SearchByFuelConsumptionCommand(testAirline);

        assertEquals(testAirline, searchCommand.getAirline());
    }
}