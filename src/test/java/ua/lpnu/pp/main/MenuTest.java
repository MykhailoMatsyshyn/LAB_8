package ua.lpnu.pp.main;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MenuTest {
    private final InputStream systemIn = System.in;

    @Before
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void testInvalidSelection() {
        String input = "7\n0\n"; // Ввід користувача: невірний вибір, вихід
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Menu menu = new Menu();
        menu.displayMenu();

        assertEquals(0, menu.getChoice()); // Перевірка, що користувач обрав вихід
    }

    @Test
    public void testExitProgram() {
        String input = "0\n"; // Ввід користувача: вихід
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Menu menu = new Menu();
        menu.displayMenu();

        assertEquals(0, menu.getChoice()); // Перевірка, що користувач обрав вихід
    }
}