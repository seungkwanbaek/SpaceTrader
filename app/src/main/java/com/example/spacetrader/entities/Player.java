package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.spacetrader.entities.ShipType.Gnat;

public class Player implements Serializable {
    private String user_name;
    private String difficulty;
    private HashMap<String, Integer> skill_points = new HashMap<String, Integer>();
    private Ship ship;
    private SolarSystem solarSystem;
    private int currentCredit;
    private long usedCapacity;
    private int shipCapacity;
    private double fuel;

    public Player() { }

    public Player(Player player) {
        this.user_name = player.user_name;
        this.difficulty = player.difficulty;
        this.skill_points.put("Pilot", player.skill_points.get("Pilot"));
        this.skill_points.put("Fighter", player.skill_points.get("Fighter"));
        this.skill_points.put("Trader", player.skill_points.get("Trader"));
        this.skill_points.put("Engineer", player.skill_points.get("Engineer"));
        this.solarSystem = player.solarSystem;
        this.currentCredit = 1000;
    }

    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_, SolarSystem solarSystem) {
        this(user_name_, difficulty_, skill_points_, new Ship(ShipType.Gnat), solarSystem, 1000);
    }

    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_, Ship ship_type_, SolarSystem solarSystem, int currentCredit) {
        this.user_name = user_name_;
        this.difficulty = difficulty_;
        this.ship = ship_type_;
        this.skill_points.put("Pilot", skill_points_.get(0));
        this.skill_points.put("Fighter", skill_points_.get(1));
        this.skill_points.put("Trader", skill_points_.get(2));
        this.skill_points.put("Engineer", skill_points_.get(3));
        this.solarSystem = solarSystem;
        this.currentCredit = currentCredit;
        this.usedCapacity = ship.getTotalCargoAmount();
        this.shipCapacity = ship.getCargoCapacity();
        this.fuel = ship.getFuelAmount();
    }

    public String getUserName() { return user_name; }
    public String getDifficulty() { return difficulty; }

    public Integer getSkillPoint(String skill_name) { return skill_points.get(skill_name); }
    public HashMap getSkillPoints() { return skill_points; }

    public Ship getShip() { return ship; }
    public long getUsedCapacity() {
        this.usedCapacity = ship.getTotalCargoAmount();
        return usedCapacity;
    }
    public int getShipCapacity() {
        this.shipCapacity = ship.getCargoCapacity();
        return shipCapacity;
    }
    public double getFuel() {
        this.fuel = ship.getFuelAmount();
        return fuel;
    }

    public SolarSystem getSolarSystem() { return solarSystem; }
    public HashMap<String, Long> getCargo() { return this.ship.getCargo(); }

    public void loadCargo(String resourceName, int amount) { this.ship.loadCargo(resourceName, amount); }
    public void unloadCargo(String resourceName, int amount) { this.ship.unloadCargo(resourceName, amount); }

    public void setSkillPoints(Map<String, Integer> skillPoints) {
        this.skill_points.put("Pilot", skillPoints.get("Pilot"));
        this.skill_points.put("Fighter", skillPoints.get("Fighter"));
        this.skill_points.put("Trader", skillPoints.get("Trader"));
        this.skill_points.put("Engineer", skillPoints.get("Engineer"));
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    /**
     * Setter for skill_name
     * @param skill_points the skill pints
     */
    public void setSkill_points(HashMap<String, Integer> skill_points) {
        for (String key : skill_points.keySet()) {
            this.skill_points.put(key, skill_points.get(key));
        }
    }

    /**
     * Setter for ship_type
     * @param ship the ship_type
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setSolarSystem(SolarSystem solarSystem) { this.solarSystem = solarSystem; }

    public int getCurrentCredit() {
        return currentCredit;
    }

    public void cost(int credit) { this.currentCredit -= credit; }

    public void deposit(int credit) { this.currentCredit += credit; }

    public void travel(SolarSystem destination, double costFuel) {
        this.solarSystem = destination;
        this.ship.useFuel(costFuel);
    }

}