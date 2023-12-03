package ua.lpnu.pp.commands;

import ua.lpnu.pp.models.Aircraft;
import ua.lpnu.pp.models.Airline;

import java.util.Scanner;

/**
 * Команда для додавання літака до авіакомпанії.
 */
public class AddPlaneCommand implements Command {
    private Airline airline;

    /**
     * Конструктор, що приймає авіакомпанію.
     *
     * @param airline Авіакомпанія, до якої додається літак.
     */
    public AddPlaneCommand(Airline airline) {
        this.airline = airline;
    }

    /**
     * Додає літак до авіакомпанії.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть виробника:");
        String manufacturer = scanner.nextLine();
        System.out.println("Введіть модель:");
        String model = scanner.nextLine();
        System.out.println("Введіть номер:");
        String serialNumber = scanner.nextLine();
        int capacity = readValidInteger(scanner, "Введіть місткість:");
        double cargoCapacity = readValidDouble(scanner, "Введіть вантажопідйомність:");
        double fuelConsumption = readValidDouble(scanner, "Введіть споживання пального:");
        double flightRange = readValidDouble(scanner, "Введіть дальність польоту:");

        Aircraft newAircraft = new Aircraft(manufacturer, model, serialNumber, capacity, cargoCapacity, fuelConsumption, flightRange);
        airline.addAircraft(newAircraft);
        System.out.println("\n\u001B[32mЛітак успішно додано до авіакомпанії.\u001B[0m");
    }

    /**
     * Зчитує та перевіряє введене значення на ціле число у вказаному діапазоні.
     *
     * @param scanner Сканер для введення даних з консолі.
     * @param prompt  Повідомлення для введення даних.
     * @return Валідне ціле число.
     */
    public int readValidInteger(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.println(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value <= 0 || value >= 1000) {
                    throw new IllegalArgumentException("\u001B[31mМісткість має бути більше 0 та менше 1000\u001B[0m");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mБудь ласка, введіть числове значення.\u001B[0m");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    /**
     * Зчитує та перевіряє введене значення на дійсне число.
     *
     * @param scanner Сканер для введення даних з консолі.
     * @param prompt  Повідомлення для введення даних.
     * @return Валідне дійсне число.
     */
    public double readValidDouble(Scanner scanner, String prompt) {
        double value;
        while (true) {
            System.out.println(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value <= 0) {
                    throw new IllegalArgumentException("\u001B[31mЗначення має бути додатнім\u001B[0m");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mБудь ласка, введіть числове значення.\u001B[0m");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    /**
     * Повертає опис команди.
     *
     * @return Опис команди для використання в меню.
     */
    @Override
    public String getDescription() {
        return "Додати літак до авіакомпанії";
    }
}
