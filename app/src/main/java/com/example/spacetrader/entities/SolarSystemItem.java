package com.example.spacetrader.entities;
import java.lang.Math;

public class SolarSystemItem {
    private String solarSystemName;
    private int x;
    private int y;
    private String curSolarSystemName;
    private int cur_x;
    private int cur_y;
    private double solarSystemDistance;
    private double cost;

    public SolarSystemItem(String solarSystemName, String curSolarSystemName,
                           int x, int y, int cur_x, int cur_y) {
        this.solarSystemName = solarSystemName;
        this.curSolarSystemName = curSolarSystemName;
        this.x = x;
        this.y = y;
        this.cur_x = cur_x;
        this.cur_y = cur_y;
        this.solarSystemDistance = getDistance(x, cur_x, y, cur_y);
        this.cost = solarSystemDistance * 0.1;
    }

    public String getSolarSystemName() { return solarSystemName; }

    public double getSolarSystemDistance() { return solarSystemDistance; }

    public double getCost() { return cost; }

    private double getDistance(int x, int cur_x, int y, int cur_y) {
        return Math.sqrt(Math.pow(x - cur_x, 2) + Math.pow(y - cur_y, 2));
    }


}
