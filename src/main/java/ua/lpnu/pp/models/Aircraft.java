package ua.lpnu.pp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    private String manufacturer;
    private String model;
    private String serialNumber;
    private int capacity;
    private double cargoCapacity;
    private double fuelConsumption;
    private double flightRange;
}