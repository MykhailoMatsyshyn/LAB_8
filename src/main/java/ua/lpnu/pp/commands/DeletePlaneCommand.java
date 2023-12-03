package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;
import ua.lpnu.pp.models.Airline;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Команда для видалення літака з авіакомпанії за серійним номером.
 */
public class DeletePlaneCommand implements Command {
    private Airline airline;

    /**
     * Конструктор, що приймає посилання на авіакомпанію.
     *
     * @param airline Авіакомпанія, з якої буде видалятися літак.
     */
    public DeletePlaneCommand(Airline airline) {
        this.airline = airline;
    }


    /**
     * Виконує видалення літака за введеним серійним номером.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть серійний номер літака для видалення:");
        System.out.print("» ");
        String serialNumber = scanner.nextLine();
        System.out.println(" ");

        Iterator<Aircraft> iterator = airline.getAircrafts().iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Aircraft aircraft = iterator.next();
            if (aircraft.getSerialNumber().equals(serialNumber)) {
                iterator.remove();
                System.out.println("Літак з серійним номером " + serialNumber + " успішно видалено.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Літак з серійним номером " + serialNumber + " не знайдено.");
        }
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Видалити літак за серійним номером";
    }
}
