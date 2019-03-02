package com.example.spacetrader.entities;

public enum ShipType {
    /**
     * The cheapest and most basic type of ship.
     */
    Flea(10, 20, 2000),
    /**
     * A cheap ship. The player begins the game with a Gnat.
     */
    Gnat(15, 20, 10000),
    /**
     * A moderately priced ship with decent features.
     */
    Firefly(20,17, 25000),
    /**
     * An expensive and high-tech ship.
     */
    Mosquito(15, 13, 30000),
    /**
     * The second-largest and second-most expensive ship available.
     */
    BumbleBee(25, 15, 60000),
    /**
     * The largest and most expensive ship available.
     */
    Dragonfly(50, 25, 200000);

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

    ShipType(int cargoCapacity, int fuelCapacity, int price) {
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
        this.price = price;
    }
}
