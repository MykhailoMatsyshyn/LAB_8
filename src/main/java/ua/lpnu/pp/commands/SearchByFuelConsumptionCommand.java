package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Клас для опції "Пошук за параметрами споживання пального".
 */
public class SearchByFuelConsumptionCommand implements Command {
    private Airline airline;
    private static final Logger logger = Logger.getLogger(DeletePlaneCommand.class.getName());

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, для якої відбувається пошук за параметрами споживання пального.
     */
    public SearchByFuelConsumptionCommand(Airline airline) {
        this.airline = airline;
    }

    /**
     * Виконує пошук літаків у флоті авіакомпанії за параметрами споживання пального.
     */
    @Override
    public void execute() {
        logger.info("=== Виконання команди пошуку літаків за параметрами споживання пального ===");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть мінімальне значення споживання пального:");
        System.out.print("» ");
        double minFuelConsumption = Double.parseDouble(scanner.nextLine());
        logger.info("Введене мінімальне значення споживання пального: " + minFuelConsumption);

        System.out.println("Введіть максимальне значення споживання пального:");
        System.out.print("» ");
        double maxFuelConsumption = Double.parseDouble(scanner.nextLine());
        logger.info("Введене максимальне значення споживання пального: " + maxFuelConsumption);

        List<Aircraft> foundAircrafts = airline.findAircraftsByFuelConsumption(minFuelConsumption, maxFuelConsumption);

        if (!foundAircrafts.isEmpty()) {
            System.out.println("Літаки зі споживанням пального від " + minFuelConsumption +
                    " до " + maxFuelConsumption + ":");

            for (Aircraft aircraft : foundAircrafts) {
                System.out.println("Назва: " + aircraft.getManufacturer() + "\nСерійний номер: " + aircraft.getSerialNumber() +
                        "\n - Пального споживає: " + aircraft.getFuelConsumption());
            }
        } else {
            System.out.println("Літаків зі споживанням пального в заданому діапазоні не знайдено.");
            logger.warning("Літаків зі споживанням пального в заданому діапазоні не знайдено.");
        }
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Пошук за параметрами споживання пального";
    }

    public Airline getAirline() {
        return airline;
    }
}
