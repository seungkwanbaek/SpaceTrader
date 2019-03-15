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

    public SolarSystemItem(SolarSystem solarSystem, int x, int y, int cur_x, int cur_y) {
        this.solarSystemName = solarSystem.getName();
        this.solarSystem = solarSystem;
        this.y = y;
        this.cur_x = cur_x;
        this.cur_y = cur_y;
        this.solarSystemDistance = getDistance(x, cur_x, y, cur_y);
        this.cost = solarSystemDistance * 0.1;
    }
    public SolarSystem getSolarSystem() { return solarSystem; }

    public String getSolarSystemName() { return solarSystemName; }

    public double getSolarSystemDistance() { return solarSystemDistance; }

    public double getCost() { return cost; }

    private double getDistance(int x, int cur_x, int y, int cur_y) {
        return Math.sqrt(Math.pow(x - cur_x, 2) + Math.pow(y - cur_y, 2));
    }


}
