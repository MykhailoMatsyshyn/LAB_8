package ua.lpnu.pp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляє авіакомпанію, яка керує флотом літаків.
 */
public class Airline {
    private List<Aircraft> aircrafts;

    /**
     * Створює порожню авіакомпанію.
     */
    public Airline() {
        aircrafts = new ArrayList<>();
    }

    public Airline(List<Aircraft> initialAircrafts) {
        this.aircrafts = new ArrayList<>(initialAircrafts);
    }

    /**
     * Додає літак до флоту авіакомпанії.
     *
     * @param aircraft Літак для додавання.
     */
    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    /**
     * Отримує список літаків у флоті авіакомпанії.
     *
     * @return Список літаків.
     */
    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    /**
     * Знаходить літаки у флоті в заданому діапазоні споживання пального.
     *
     * @param minFuelConsumption Мінімальне споживання пального.
     * @param maxFuelConsumption Максимальне споживання пального.
     * @return Список літаків у вказаному діапазоні споживання пального.
     */
    public List<Aircraft> findAircraftsByFuelConsumption(double minFuelConsumption, double maxFuelConsumption) {
        List<Aircraft> result = new ArrayList<>();
        for (Aircraft aircraft : aircrafts) {
            double fuelConsumption = aircraft.getFuelConsumption();
            if (fuelConsumption >= minFuelConsumption && fuelConsumption <= maxFuelConsumption) {
                result.add(aircraft);
            }
        }
        return result;
    }
}
