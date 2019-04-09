package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The player class
 */
public class Player implements Serializable {
    private String user_name;
    private String difficulty;
    private HashMap<String, Integer> skill_points = new HashMap<>();
    private Ship ship;
    private SolarSystem solarSystem;
    private int currentCredit;
    private long usedCapacity;
    private int shipCapacity;
    private double fuel;

    /**
     * Default Constructor for Player
     */
    public Player() { }

    /**
     * Constructor of player with given player
     * @param player given player
     */
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

    /**
     * Constructor of player with given name, difficulty, skillPoints and solarSystem
     * @param user_name_ given userName
     * @param difficulty_ given difficulty
     * @param skill_points_ given skillPoints
     * @param solarSystem given solarSystem
     */
    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_, SolarSystem solarSystem) {
        this(user_name_, difficulty_, skill_points_, new Ship(ShipType.Gnat), solarSystem, 1000);
    }

    /**
     * Constructor of player
     * @param user_name_ given userName
     * @param difficulty_ given difficulty
     * @param skill_points_ given skillPoints
     * @param ship_type_ given shipType
     * @param solarSystem given solarSystem
     * @param currentCredit given currentCredit
     */
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

    /**
     * Getter for userName
     * @return the userName
     */
    public String getUserName() {
        return user_name;
    }

    /**
     * Getter for difficulty
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Getter for skill point
     * @param skill_name the skill name
     * @return the skill point
     */
    public Integer getSkillPoint(String skill_name) {
        return skill_points.get(skill_name);
    }

    /**
     * Getter for skillPoints
     * @return the map of skillPoints allocation
     */
    public Map getSkillPoints() {
        return skill_points;
    }

    /**
     * Getter for ship
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Getter for usedCapacity
     * @return the used capacity
     */
    public long getUsedCapacity() {
        this.usedCapacity = ship.getTotalCargoAmount();
        return usedCapacity;
    }

    /**
     * Getter for shipCapacity
     * @return the ship capacity
     */
    public int getShipCapacity() {
        this.shipCapacity = ship.getCargoCapacity();
        return shipCapacity;
    }

    /**
     * Getter for fuel
     * @return the fuel
     */
    public double getFuel() {
        this.fuel = ship.getFuelAmount();
        return fuel;
    }

    /**
     * Getter for current credit
     * @return the current credit
     */
    public int getCurrentCredit() {
        return currentCredit;
    }

    /**
     * Getter for solar system
     * @return the solar system
     */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     * Getter for cargo
     * @return the cargo
     */
    public Map<String, Long> getCargo() {
        return this.ship.getCargo();
    }

    /**
     * Set skillPoints
     * @param skillPoints the skillPoints to set
     */
    public void setSkillPoints(Map<String, Integer> skillPoints) {
        this.skill_points.put("Pilot", skillPoints.get("Pilot"));
        this.skill_points.put("Fighter", skillPoints.get("Fighter"));
        this.skill_points.put("Trader", skillPoints.get("Trader"));
        this.skill_points.put("Engineer", skillPoints.get("Engineer"));
    }

    /**
     * Setter for usedCapacity
     * @param usedCapacity the used capacity
     */
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

    /**
     * Setter for solarSystem
     * @param solarSystem the solarSystem
     */

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * Load cargo
     * @param resourceName the resource name
     * @param amount the amount
     */
    public void loadCargo(String resourceName, int amount) {
        this.ship.loadCargo(resourceName, amount);
    }

    /**
     * Unload Cargo
     * @param resourceName the resource name
     * @param amount the amount
     */
    public void unloadCargo(String resourceName, int amount) {
        this.ship.unloadCargo(resourceName, amount);
    }

    /**
     * Cost certain credit
     * @param credit the credit amount
     */
    public void cost(int credit) {
        this.currentCredit -= credit;
    }

    /**
     * Deposit certain credit
     * @param credit the credit amount
     */
    public void deposit(int credit) {
        this.currentCredit += credit;
    }

    /**
     * Travel to destination solarSystem with fuel
     * @param destination the destination solarSystem
     * @param costFuel cost fuel
     */
    public void travel(SolarSystem destination, double costFuel) {
        this.solarSystem = destination;
        this.ship.useFuel(costFuel);
    }

}