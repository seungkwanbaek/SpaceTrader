package com.example.spacetrader.entities;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Model of a ship.
 *
 * @author Bryan
 */
public class Ship implements Serializable {
    private ShipType type;
    private Map<String, Long> cargo;
    private double fuelAmount;

    /**
     * Default Constructor for ship
     */
    public Ship() {}

    /**
     * Create a new ship of a given type and set its owner.
     * @param type  The type of the new ship.
     */
    public Ship(ShipType type) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.cargo = new HashMap<>();
    }

    /**
     * Constructor of ship with type, cargo and fuelAmount
     * @param type the type
     * @param cargo the cargo
     * @param fuelAmount the fuel amount
     */
    public Ship(ShipType type, Map<String, Long> cargo, double fuelAmount) {
        this.type = type;
        this.cargo = cargo;
        this.fuelAmount = fuelAmount;
    }
    /**
     * Getter method for type
     * @return The type of the ship
     */
    public ShipType getType() {
        return type;
    }

    /**
     * Getter method for cargo capacity
     * @return the cargo capacity
     */
    public int getCargoCapacity() {
        return type.cargoCapacity;
    }

    /**
     * Getter method for fuel capacity
     * @return the fuel capacity
     */
    public int getFuelCapacity() {
        return type.fuelCapacity;
    }

    /**
     * Getter method for cargo
     * @return The ship's cargo hold
     */
    public Map<String, Long> getCargo() {
        return cargo;
    }

    /**
     * Load cargo with resource name and amount
     * @param resourceName the resource name
     * @param amount the resource amount
     */
    public void loadCargo(String resourceName, long amount) {
        if (amount == 0) return;
        Long currentAmount = cargo.get(resourceName);
        if (currentAmount == null) cargo.put(resourceName, amount);
        else cargo.put(resourceName, amount+currentAmount);
    }

    /**
     * Unload cargo with resource and amount
     * @param resourceName the resource name
     * @param amount the resource amount
     */
    public void unloadCargo(String resourceName, int amount) {
        if (!cargo.containsKey(resourceName)) {
            Log.d("[TEST]", "No " + resourceName + " resource!");
            return;
        }
        if (amount == 0) return;
        Long currentAmount = cargo.get(resourceName);
        assert(currentAmount != null && currentAmount >= amount);
        Log.d("[TEST]", resourceName+" "+Long.toString(currentAmount)+" "+Integer.toString(amount));
        if (currentAmount == amount) cargo.remove(resourceName);
        else cargo.put(resourceName, currentAmount-amount);
    }

    /**
     * Getter for total cargoAmount
     * @return the total cargo amount
     */
    public long getTotalCargoAmount() {
        long totalAmount = 0;
        for (String r : cargo.keySet()) totalAmount += cargo.get(r);
        return totalAmount;
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
        double currentAmount = fuelAmount + amount;
        if (currentAmount > type.fuelCapacity) {
            fuelAmount = type.fuelCapacity;
        } else {
            fuelAmount += amount;
        }
    }

    /**
     * use fuel method
     * @param amount the used fuel amount
     */
    public void useFuel(double amount) {
        fuelAmount -= amount;
    }

    /**
     * Restores ship fuel to maximum.
     */
    public void refuel() {
        fuelAmount = type.fuelCapacity;
    }


}
