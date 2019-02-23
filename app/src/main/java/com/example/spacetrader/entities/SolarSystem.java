package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SolarSystem implements Serializable {

    private String name;
//    private String techLevel;
//    private String resources;
    private HashMap<Integer, Integer> priceList;
    private Random rand;
    private int x;
    private int y;
    private static String[] techLevel = { "Pre-Agriculture", "Agriculture",
            "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"};
    private static String[] resources = {"NOSPECIALRESOURCES", "MINERALRICH", "MINERALPOOR",
            "DESERT", "LOTSOFWATER", "RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS",
            "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE"};

    public SolarSystem(String name, int xcoord, int ycoord) {
        this.name = name;
        this.x = xcoord;
        this.y = ycoord;
        priceList = new HashMap<>();


    }

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
    public boolean equals(Object that) {
        SolarSystem rhs = (SolarSystem) that;
        if (rhs.name.equals(this.name)) {
            return true;
        }
        if (this.x == rhs.x && this.y == rhs.y) {
            return true;
        }
        return false;
    }

    public String getName() { return name; }

    public String getResources() { return resources; }

    public HashMap getPriceList() { return priceList; }

    public String getTechLevel() { return techLevel; }
}
