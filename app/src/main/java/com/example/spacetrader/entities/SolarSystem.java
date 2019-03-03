package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SolarSystem implements Serializable {

    private String name;
    private String techLevel;
    private String resources;
    private Random rand = new Random();
    private int x;
    private int y;
    private static String[] techLevelList = { "Pre-Agriculture", "Agriculture",
            "Medieval", "Renaissance", "Early Industrial", "Industrial",
            "Post-Industrial", "Hi-Tech"};
    private static String[] resourcesList = {"NOSPECIALRESOURCES", "MINERALRICH", "MINERALPOOR",
            "DESERT", "LOTSOFWATER", "RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS",
            "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE"};

    public SolarSystem() {
        this.name = generateSolarName();
        int[] coordinates = generateCoordinate();
        this.x = coordinates[0];
        this.y = coordinates[1];
        int r1 = rand.nextInt(techLevelList.length);
        int r2 = rand.nextInt(resourcesList.length);
        techLevel = techLevelList[r1];
        resources = resourcesList[r2];
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

    public String getTechLevel() { return techLevel; }

    /**
     * Generate the coordinate randomly
     * @return the corrdinates
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

    public void printSolarSystem() {
        System.out.println("Name: " + name + ", x_coord: " + x + ", y_coord: " + y +
                " resources:" + resources + ", techLevel: " + techLevel);
    }

    public String toString() {
        return "Name: " + name + ", x_coord: " + x + ", y_coord: " + y +
                " resources:" + resources + ", techLevel: " + techLevel;
    }

}
