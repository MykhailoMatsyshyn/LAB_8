package ua.lpnu.pp.commands;

import org.junit.Before;
import org.junit.Test;
import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListPlanesCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Airline testAirline;
    private ListPlanesCommand listPlanesCommand;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        testAirline = new Airline();
        listPlanesCommand = new ListPlanesCommand(testAirline);
    }

    @Test
    public void testExecuteWhenNoPlanes() {
        String expected = "―――――――― Інформація про всі літаки: ―――――――\r\n" +
                "У авіакомпанії немає жодного літака.\r\n";
        listPlanesCommand.execute();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithPlanes() {
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(new Aircraft("Boeing", "747", "12345", 300, 4000.0, 20.5, 8000.0));
        aircraftList.add(new Aircraft("Airbus", "A320", "54321", 150, 2500.0, 15.0, 6000.0));

        testAirline = new Airline(aircraftList);
        listPlanesCommand = new ListPlanesCommand(testAirline);

        listPlanesCommand.execute();

        String expectedOutput = "―――――――― Інформація про всі літаки: ―――――――\r\n" +
                " Виробник: Boeing\r\n" +
                " Модель: 747\r\n" +
                " Серійний номер: 12345\r\n" +
                " Місткість: 300\r\n" +
                " Вантажопідйомність: 4000.0\r\n" +
                " Споживання пального: 20.5\r\n" +
                " Дальність польоту: 8000.0\r\n" +
                "―――――――――――――――――――――――――――――――――――――――――――\r\n" +
                " Виробник: Airbus\r\n" +
                " Модель: A320\r\n" +
                " Серійний номер: 54321\r\n" +
                " Місткість: 150\r\n" +
                " Вантажопідйомність: 2500.0\r\n" +
                " Споживання пального: 15.0\r\n" +
                " Дальність польоту: 6000.0\r\n" +
                "―――――――――――――――――――――――――――――――――――――――――――\r\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testGetDescription() {
        ListPlanesCommand listPlanesCommand = new ListPlanesCommand(testAirline);
        String expectedDescription = "Вивести всі літаки";
        assertEquals(expectedDescription, listPlanesCommand.getDescription());
    }

}