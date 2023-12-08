package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Клас для опції "Сортування за дальністю польоту".
 */
public class SortByFlightRangeCommand implements Command {
    private Airline airline;
    private static final Logger logger = Logger.getLogger(SortByFlightRangeCommand.class.getName());

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, для якої відбувається сортування за дальністю польоту.
     */
    public SortByFlightRangeCommand(Airline airline) {
        this.airline = airline;
    }

    public Airline getAirline() {
        return airline;
    }

    /**
     * Виконує сортування літаків за дальністю польоту та виводить відсортований список.
     */
    @Override
    public void execute() {
        logger.info("=== Виконання команди сортування за дальністю польоту ===");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть сортування:");
        System.out.println("1. За зростанням дальності польоту");
        System.out.println("2. За спаданням дальності польоту");
        System.out.print("» ");
        int choice = scanner.nextInt();
        System.out.println(" ");

        List<Aircraft> aircrafts = airline.getAircrafts();

        if (choice == 1) {
            logger.info("Сортування за зростанням дальності польоту");
            aircrafts.sort(Comparator.comparingDouble(Aircraft::getFlightRange));
        } else if (choice == 2) {
            logger.info("Сортування за спаданням дальності польоту");
            aircrafts.sort((a1, a2) -> Double.compare(a2.getFlightRange(), a1.getFlightRange()));
        } else {
            logger.warning("Невірний вибір");
            System.out.println("Невірний вибір!");
            return;
        }

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
