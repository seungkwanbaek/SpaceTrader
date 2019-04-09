package com.example.spacetrader.entities;

/**
 * The shipType class
 */
public enum ShipType {
    /**
     * The cheapest and most basic type of ship.
     */
    Flea(10, 20, 2000, "Flea"),
    /**
     * A cheap ship. The player begins the game with a Gnat.
     */
    Gnat(15, 20, 10000, "Gnat");


    /**
     * The ship's maximum cargo capacity.
     */
    public final int cargoCapacity;

    /**
     * The maximum amount of fuel the ship can hold.
     */
    public final int fuelCapacity;

    /**
     * The price of the ship.
     */
    public final int price;


    /**
     * The price of the ship.
     */
    private final String name;

    /**
     * Constructor for ship type
     * @param cargoCapacity the cargo capacity
     * @param fuelCapacity the fuel capacity
     * @param price the price
     * @param name the name
     */
    ShipType(int cargoCapacity, int fuelCapacity, int price, String name) {
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
        this.price = price;
        this.name = name;
    }

    /**
     * to string method
     * @return the string of ship type
     */
    public String toString() {
        return name;
    }

}
