package com.example.spacetrader.entities;
import java.lang.Math;

public class SolarSystemItem {
    private String solarSystemName;
    private SolarSystem solarSystem;
    private int x;
    private int y;
    private int cur_x;
    private int cur_y;
    private double solarSystemDistance;
    private double cost;

    /**
     * Constructor of SolarSystemItem
     * @param solarSystem the solarSystem
     * @param x the x location
     * @param y the y location
     * @param cur_x the current x location
     * @param cur_y the current y location
     */
    public SolarSystemItem(SolarSystem solarSystem, int x, int y, int cur_x, int cur_y) {
        this.solarSystemName = solarSystem.getName();
        this.solarSystem = solarSystem;
        this.y = y;
        this.cur_x = cur_x;
        this.cur_y = cur_y;
        this.solarSystemDistance = getDistance(x, cur_x, y, cur_y);
        this.cost = solarSystemDistance * 0.1;
    }

    /**
     * Getter method for solar system
     * @return the solar system
     */
    public SolarSystem getSolarSystem() { return solarSystem; }

    /**
     * Getter method for solar system name
     * @return the solar system name
     */
    public String getSolarSystemName() { return solarSystemName; }

    /**
     * Getter method for distance
     * @return the distance
     */
    public double getSolarSystemDistance() { return solarSystemDistance; }

    /**
     * Getter method for cost
     * @return the cost
     */
    public double getCost() { return cost; }

    /**
     * Calculate distance of two locations
     * @param x the x location
     * @param cur_x the current x location
     * @param y the y location
     * @param cur_y the current y location
     * @return the distance
     */
    private double getDistance(int x, int cur_x, int y, int cur_y) {
        return Math.sqrt(Math.pow(x - cur_x, 2) + Math.pow(y - cur_y, 2));
    }


}
