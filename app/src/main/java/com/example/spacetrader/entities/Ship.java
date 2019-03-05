package com.example.spacetrader.entities;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Model of a ship.
 *
 * @author Bryan
 */
public class Ship implements Serializable {
    private ShipType type;
    //private CargoHold cargoHold;
    private HashMap<String, Integer> cargo;
    private double fuelAmount;

    /**
     * Create a new ship of a given type and set its owner.
     *
     * @param type  The type of the new ship.
     */
    public Ship(ShipType type) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.cargo = new HashMap<>();
    }

    /**
     * @return The type of the ship
     */
    public ShipType getType() {
        return type;
    }

    public int getCargoCapacity() {
        return type.cargoCapacity;
    }

    public int getFuelCapacity() {
        return type.fuelCapacity;
    }

    /**
     * @return The ship's cargo hold
     */
    public HashMap<String, Integer> getCargo() {
        return cargo;
    }

    public void loadCargo(String resourceName, int amount) {
        if (amount == 0) return;
        Integer currentAmount = cargo.get(resourceName);
        if (currentAmount == null) cargo.put(resourceName, amount);
        else cargo.put(resourceName, amount+currentAmount);
    }

    public void unloadCargo(String resourceName, int amount) {
        if (amount == 0) return;
        Integer currentAmount = cargo.get(resourceName);
        assert(currentAmount != null && currentAmount >= amount);
        Log.d("[TEST]", resourceName+" "+Integer.toString(currentAmount)+" "+Integer.toString(amount));
        if (currentAmount == amount) cargo.remove(resourceName);
        else cargo.put(resourceName, currentAmount-amount);
    }

    public int getTotalCargoAmount() {
        int totalAmount = 0;
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
