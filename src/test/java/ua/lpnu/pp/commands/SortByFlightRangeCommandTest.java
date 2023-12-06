package ua.lpnu.pp.commands;

import org.junit.Before;
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

public class SortByFlightRangeCommandTest {
    private final InputStream systemIn = System.in;
    private final Airline testAirline = new Airline();
    private final List<Aircraft> testAircraftList = new ArrayList<>();

    @Before
    public void setUp() {
        testAircraftList.add(new Aircraft("Boeing", "747", "12345", 300, 4000.0, 20.5, 8000.0));
        testAircraftList.add(new Aircraft("Airbus", "A320", "54321", 150, 2500.0, 15.0, 6000.0));
        testAirline.getAircrafts().addAll(testAircraftList);
    }

    @Test
    public void testExecuteSortingByAscending() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(testAirline);
        sortByFlightRangeCommand.execute();

        List<Aircraft> sortedAircrafts = testAirline.getAircrafts();
        sortedAircrafts.sort((a1, a2) -> Double.compare(a1.getFlightRange(), a2.getFlightRange()));

        Aircraft expectedFirstAircraft = new Aircraft("Airbus", "A320", "54321", 150, 2500.0, 15.0, 6000.0);
        Aircraft actualFirstAircraft = sortedAircrafts.get(0);

        assertEquals(expectedFirstAircraft, actualFirstAircraft);
    }

    @Test
    public void testExecuteSortingByDescending() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(testAirline);
        sortByFlightRangeCommand.execute();

        List<Aircraft> sortedAircrafts = testAirline.getAircrafts();
        sortedAircrafts.sort((a1, a2) -> Double.compare(a2.getFlightRange(), a1.getFlightRange()));

        assertEquals(testAircraftList, sortedAircrafts);
    }

    @Test
    public void testExecuteInvalidChoice() {
        String input = "3\n"; // Ввід користувача, що є недопустимим
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(testAirline);
        sortByFlightRangeCommand.execute();

        String expectedOutput = "Оберіть сортування:\r\n" +
                "1. За зростанням дальності польоту\r\n" +
                "2. За спаданням дальності польоту\r\n" +
                "»  \r\n" +
                "Невірний вибір!\r\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testGetAirline() {
        Airline testAirline = new Airline();
        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(testAirline);

        assertEquals(testAirline, sortByFlightRangeCommand.getAirline());
    }

    @Test
    public void testConstructor() {
        Airline testAirline = new Airline(); // Створення об'єкту Airline для тестування конструктора
        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(testAirline);

        assertNotNull(sortByFlightRangeCommand);
        assertEquals(testAirline, sortByFlightRangeCommand.getAirline());
    }

    @Test
    public void testGetDescription() {
        SortByFlightRangeCommand sortByFlightRangeCommand = new SortByFlightRangeCommand(null);
        String expectedDescription = "Сортування за дальністю польоту";

        assertEquals(expectedDescription, sortByFlightRangeCommand.getDescription());
    }
}