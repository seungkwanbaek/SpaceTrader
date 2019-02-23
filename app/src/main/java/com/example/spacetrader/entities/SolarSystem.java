package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SolarSystem implements Serializable {

    private String name;
    private String techLevel;
    private String resources;
    private HashMap<Integer, Integer> priceList;
    private Random rand;
    private int x;
    private int y;

    public SolarSystem(String name_, String techLevel_, String resources_,
                       ArrayList<Commodity> commodityList, int xcoord, int ycoord) {
        rand = new Random();
        name = name_;
        resources = resources_;
        techLevel = techLevel_;
        for (int i = 0; i < commodityList.size(); i++) {
            Commodity c = commodityList.get(i);
            priceList.put(c.getId(), rand.nextInt(100));
        }
        x = xcoord;
        y = ycoord;
    }

    @Override
    public int hashCode() {
        return 1000000 * x + y;
    }

    @Override
    public Boolean equals(SolarSystem that) {
        if (this.x == that.x && this.y == that.y) {
            return true;
        } else return false;
    }

    public String getName() { return name; }

    public String getResources() { return resources; }

    public HashMap getPriceList() { return priceList; }

    public String getTechLevel() { return techLevel; }

    public int getId() { return id; }

}