package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Клас для опції "Сортування за дальністю польоту".
 */
public class SortByFlightRangeCommand implements Command {
    private Airline airline;

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, для якої відбувається сортування за дальністю польоту.
     */
    public SortByFlightRangeCommand(Airline airline) {
        this.airline = airline;
    }

    /**
     * Виконує сортування літаків за дальністю польоту та виводить відсортований список.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть сортування:");
        System.out.println("1. За зростанням дальності польоту");
        System.out.println("2. За спаданням дальності польоту");
        System.out.print("» ");
        int choice = scanner.nextInt();
        System.out.println(" ");

        // Отримання списку літаків у флоті авіакомпанії
        List<Aircraft> aircrafts = airline.getAircrafts();

        if (choice == 1) {
            // Сортування за зростанням дальності польоту
            aircrafts.sort(Comparator.comparingDouble(Aircraft::getFlightRange));
        } else if (choice == 2) {
            // Сортування за спаданням дальності польоту
            aircrafts.sort((a1, a2) -> Double.compare(a2.getFlightRange(), a1.getFlightRange()));
        } else {
            System.out.println("Невірний вибір!");
            return;
        }

        // Виведення відсортованого списку літаків
        System.out.println("\nЛітаки відсортовані за дальністю польоту:");
        for (Aircraft aircraft : aircrafts) {
            System.out.println(aircraft.getModel() + " (" + aircraft.getSerialNumber() + ") - " + aircraft.getFlightRange());
        }
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Сортування за дальністю польоту";
    }
}
