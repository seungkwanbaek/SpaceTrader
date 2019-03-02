package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model of a ship.
 *
 * @author Bryan
 */
public class Ship implements Serializable {
    private ShipType type;
    private CargoHold cargoHold;
    private double fuelAmount;

    /**
     * Create a new ship of a given type and set its owner.
     *
     * @param type  The type of the new ship.
     * @param owner The owner of the new ship.
     */
    public Ship(ShipType type) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.cargoHold = new CargoHold(type.cargoCapacity);
    }

    /**
     * @return The type of the ship
     */
    public ShipType getType() {
        return type;
    }

    /**
     * @return The ship's cargo hold
     */
    public CargoHold getCargoHold() {
        return cargoHold;
    }

    /**
     * @return The amount of fuel currently available in the ship
     */
    public double getFuelAmount() {
        return fuelAmount;
    }

    /**
     * Expend the amount of fuel necessary to travel a given distance.
     *
     * @param distance The distance traveled
     */
    public void expendFuel(double distance) {
        fuelAmount -= distance;
    }

    /**
     * Adds fuel to ship.
     *
     * @param amount amount of fuel to be added
     */
    public void addFuel(double amount) {
        if (fuelAmount + amount > type.fuelCapacity) {
            fuelAmount = type.fuelCapacity;
        } else {
            fuelAmount += amount;
        }
    }

    /**
     * Restores ship fuel to maximum.
     */
    public void refuel() {
        fuelAmount = type.fuelCapacity;
    }
}
