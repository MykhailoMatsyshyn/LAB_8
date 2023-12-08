package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;
import java.util.List;
import java.util.logging.Logger;

/**
 * Команда для виведення інформації про всі літаки авіакомпанії.
 */
public class ListPlanesCommand implements Command {
    private Airline airline;
    private static final Logger logger = Logger.getLogger(DeletePlaneCommand.class.getName());

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, для якої виводиться інформація про літаки.
     */
    public ListPlanesCommand(Airline airline) {
        this.airline = airline;
    }

    /**
     * Виводить інформацію про всі літаки авіакомпанії.
     */
    @Override
    public void execute() {
        logger.info("=== Виконання команди виведення інформації про літаки ===");

        System.out.println("―――――――― Інформація про всі літаки: ―――――――");

        List<Aircraft> aircrafts = airline.getAircrafts();

        if (aircrafts.isEmpty()) {
            System.out.println("У авіакомпанії немає жодного літака.");
            logger.warning("У авіакомпанії немає жодного літака.");
        } else {
            for (Aircraft aircraft : aircrafts) {
                System.out.println(" Виробник: " + aircraft.getManufacturer());
                System.out.println(" Модель: " + aircraft.getModel());
                System.out.println(" Серійний номер: " + aircraft.getSerialNumber());
                System.out.println(" Місткість: " + aircraft.getCapacity());
                System.out.println(" Вантажопідйомність: " + aircraft.getCargoCapacity());
                System.out.println(" Споживання пального: " + aircraft.getFuelConsumption());
                System.out.println(" Дальність польоту: " + aircraft.getFlightRange());
                System.out.println("―――――――――――――――――――――――――――――――――――――――――――");
            }
            logger.info("Список виведено");
        }
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Вивести всі літаки";
    }
}
