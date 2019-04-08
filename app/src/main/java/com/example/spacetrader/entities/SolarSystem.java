package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SolarSystem implements Serializable {

    private String name;
    private String techLevel;
    private int techLevelValue;
    private String resourceDescrption;
    private Random rand = new Random();
    private int x;
    private int y;
    //private final ResourcesList resourcesList = new ResourcesList();
    //private HashMap<Resource, Integer> pricesList;
    private static final String[] techLevelList = { "Pre-Agriculture", "Agriculture",
            "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"};
    private static final String[] resourceDescrptionList = {"NOSPECIALRESOURCES", "MINERALRICH", "MINERALPOOR",
            "DESERT", "LOTSOFWATER", "RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS",
            "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE"};

    /**
     * Constructor of solar system
     */
    public SolarSystem() {
        this.name = generateSolarName();
        int[] coordinates = generateCoordinate();
        this.x = coordinates[0];
        this.y = coordinates[1];
        this.techLevelValue = rand.nextInt(techLevelList.length);
        int r2 = rand.nextInt(resourceDescrptionList.length);
        techLevel = techLevelList[techLevelValue];
        resourceDescrption = resourceDescrptionList[r2];
    }

    /**
     * Constructor of solar system with name, description, techLevel and location
     * @param name the solar system name
     * @param resourceDescrption the resource description
     * @param techLevel the tech level
     * @param x the x location
     * @param y the y location
     */
    public SolarSystem(String name, String resourceDescrption, String techLevel, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.resourceDescrption = resourceDescrption;
        this.techLevel = techLevel;
    }
    /**
     * Override equals method
     */
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

    /**
     * Setter method for techLevelValue
     * @param tv the given techLevel
     */
    public void setTechLevelValue(int tv) {
        this.techLevelValue = tv;
    }

    /**
     * Getter method for name
     * @return the  solar system name
     */
    public String getName() { return name; }

    /**
     * Getter method for description
     * @return the resource description
     */
    public String getResourceDescrption() {
        return resourceDescrption;
    }

    /**
     * Getter method for tech level
     * @return the tech level
     */
    public String getTechLevel() {
        return techLevel;
    }

    /**
     * Getter method for techLevelValue
     * @return the techLevelValue
     */
    public int getTechLevelValue() {
        return techLevelValue;
    }

    /**
     * Getter method for x
     * @return x value
     */
    public int getX() {
        return x;
    }

    /**
     * Getter method for y
     * @return y value
     */
    public int getY() {
        return y;
    }

    /**
     * Generate the coordinate
     * @return the coordinate location
     */
    private int[] generateCoordinate() {
        int[] coordinate = new int[2];
        coordinate[0] = rand.nextInt(100);
        coordinate[1] = rand.nextInt(100);
        return coordinate;
    }

    /**
     * Generate the solar name randomly
     * @return the solar name
     */
    private String generateSolarName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char c = (char)('a' + rand.nextInt(26));
            sb.append(c);
        }
        return new String(sb);
    }

    /**
     * toString method
     * @return the toString value
     */
    public String toString() {
        return "Name: " + name + ", x_coord: " + x + ", y_coord: " + y +
                " resourceDescription:" + resourceDescrption + ", techLevel: " + techLevel;
    }

}
