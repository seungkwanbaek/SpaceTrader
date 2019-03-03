package com.example.spacetrader.entities;

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

    ShipType(int cargoCapacity, int fuelCapacity, int price, String name) {
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
        this.price = price;
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
