package ua.lpnu.pp.main;

import ua.lpnu.pp.commands.*;
import ua.lpnu.pp.models.Airline;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
        public void displayMenu() {
            CommandInvoker commandInvoker = new CommandInvoker();
            Airline airline = new Airline();
            Scanner scanner = new Scanner(System.in);
            int choice;

            Map<Integer, Command> commands = new HashMap<>();
            commands.put(1, new AddPlaneCommand(airline));
            commands.put(2, new DeletePlaneCommand(airline));
            commands.put(3, new ListPlanesCommand(airline));
            commands.put(4, new TotalCapacityCommand(airline));
            commands.put(5, new SortByFlightRangeCommand(airline));
            commands.put(6, new SearchByFuelConsumptionCommand(airline));

            do {
                System.out.println("\n============ Оберіть операцію: ============");
                for (var entry : commands.entrySet()) {
                    System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
                }
                System.out.println("0. Вийти з програми");
                System.out.println("===========================================");
                System.out.print("» ");
                choice = scanner.nextInt();
                System.out.println(" ");

                if (commands.containsKey(choice)) {
                    commandInvoker.executeCommand(commands.get(choice));
                } else if (choice == 0) {
                    System.out.println("Програма завершена.");
                } else {
                    System.out.println("Не правильно введено. Спробуйте ще раз.");
                }
            } while (choice != 0);
        }
}
