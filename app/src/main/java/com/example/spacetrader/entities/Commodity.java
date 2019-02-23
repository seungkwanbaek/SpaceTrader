package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Commodity implements Serializable{
    private Integer id;
    private String name;
    private Integer weight;


    public Commodity(Commodity commodity) {
        this.id = commodity.id;
        this.weight = commodity.weight;
        this.name = commodity.name;
    }

    public Commodity(Integer id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }

    public Integer getId() { return id; }

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
