package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Airline;

/**
 * Клас для опції "Загальна місткість та вантажопідйомність".
 */
public class TotalCapacityCommand implements Command {
    private Airline airline;

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, для якої розраховується загальна місткість та вантажопідйомність.
     */
    public TotalCapacityCommand(Airline airline) {
        this.airline = airline;
    }

    /**
     * Виконує розрахунок та виводить загальну місткість та вантажопідйомність.
     */
    @Override
    public void execute() {
        int totalCapacity = 0;
        double totalCargoCapacity = 0.0;

        for (var aircraft : airline.getAircrafts()) {
            totalCapacity += aircraft.getCapacity();
            totalCargoCapacity += aircraft.getCargoCapacity();
        }

        System.out.println("Загальна пасажиромісткість усіх літаків: " + totalCapacity);
        System.out.println("Загальна вантажопідйомність усіх літаків: " + totalCargoCapacity);
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Загальна місткість та вантажопідйомність";
    }
}
