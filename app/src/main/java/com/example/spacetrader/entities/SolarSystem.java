package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SolarSystem implements Serializable {
    private Integer id;

    private String name;

    private String techLevel;

    private String resources;

    private HashMap<Integer, Integer> priceList;

    private Random rand;

    public SolarSystem(Integer id_, String name_, String techLevel_, String resources_, ArrayList<Commodity> commodityList) {
        rand = new Random();
        id = id_;
        name = name_;
        resources = resources_;
        techLevel = techLevel_;
        for (int i = 0; i < commodityList.size(); i++) {
            Commodity c = commodityList.get(i);
            priceList.put(c.getId(), rand.nextInt(100));
        }
    }

    public String getName() { return name; }

    public String getResources() { return resources; }

    public HashMap getPriceList() { return priceList; }

    public String getTechLevel() { return techLevel; }

    public int getId() { return id; }

}
