// Авіакомпанія.
// Визначити ієрархію літаків.
// Створити авіакомпанію.
// Підрахувати загальну місткість і вантажопідйомність.
// Здійснити сортування літаків компанії за дальністю польоту.
// Знайти літак у компанії, що відповідає заданому діапазону параметрів споживання пального.

package ua.lpnu.pp.main;

import ua.lpnu.pp.Logs.LoggerSetup;

public class AirlineManagementSystem {
    public static void main(String[] args) {
        LoggerSetup.setupLogger();
        Menu menu = new Menu();
        menu.displayMenu();
    }
}