package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Commodity implements Serializable{
    private String name;
    private int weight;


    public Commodity(Commodity commodity) {
        this.weight = commodity.weight;
        this.name = commodity.name;
    }

    public Commodity(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }

    public Integer getWeight() { return weight; }

    /**
     * Setter for commodity name
     * @param name the commodity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for weight
     * @param weight the weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

}
