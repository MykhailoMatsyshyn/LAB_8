package ua.lpnu.pp.commands;

import org.junit.Test;
import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AddPlaneCommandTest {
    @Test
    public void testReadValidInteger() {
        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(new Airline());
        InputStream inputStream = System.in;
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int result = addPlaneCommand.readValidInteger(new Scanner(System.in), "Enter a number:");
        assertEquals(5, result);

        System.setIn(inputStream);
    }

    @Test
    public void testReadValidDouble() {
        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(new Airline());
        InputStream inputStream = System.in;
        String input = "10.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        double result = addPlaneCommand.readValidDouble(new Scanner(System.in), "Enter a number:");
        assertEquals(10.5, result, 0.001);

        System.setIn(inputStream);
    }

    @Test
    public void testExecute_InputValues_AddsPlaneToAirline() {
        String input = "Boeing\n747\nABC123\n200\n150.5\n25.0\n5000.0\n";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Airline airline = new Airline();
        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(airline);

        addPlaneCommand.execute();

        List<Aircraft> aircrafts = airline.getAircrafts();
        assertEquals(1, aircrafts.size());

        Aircraft addedAircraft = aircrafts.get(0);
        assertEquals("Boeing", addedAircraft.getManufacturer());
        assertEquals("747", addedAircraft.getModel());
        assertEquals("ABC123", addedAircraft.getSerialNumber());
        assertEquals(200, addedAircraft.getCapacity());
        assertEquals(150.5, addedAircraft.getCargoCapacity(), 0.001);
        assertEquals(25.0, addedAircraft.getFuelConsumption(), 0.001);
        assertEquals(5000.0, addedAircraft.getFlightRange(), 0.001);

        System.setIn(inputStream);
    }

    @Test
    public void testGetDescription_ReturnsCorrectDescription() {
        Airline airline = new Airline();
        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(airline);

        String expectedDescription = "Додати літак до авіакомпанії";
        String actualDescription = addPlaneCommand.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testReadValidDouble_() {
        String input = "abc\n-5\n50.0\n"; // Рядок із неправильними введеннями: текст, від'ємне число та коректне значення
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(new Airline());

        double result = addPlaneCommand.readValidDouble(scanner, "Введіть вантажопідйомність:");

        assertEquals(50.0, result, 0.001); // Перевірка, чи було правильно прочитане коректне значення
    }

    @Test
    public void testReadValidInteger_() {
        String input = "abc\n0\n1000\n500\n"; // Рядок із неправильними введеннями: текст, 0, 1000 та коректне значення
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        AddPlaneCommand addPlaneCommand = new AddPlaneCommand(new Airline());

        int result = addPlaneCommand.readValidInteger(scanner, "Введіть місткість:");

        assertEquals(500, result); // Перевірка, чи було правильно прочитане коректне значення
    }
}