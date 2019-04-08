package com.example.spacetrader.model;

import android.util.Log;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.entities.SolarSystem;

import java.util.ArrayList;
import java.util.List;



/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
public class Repository {

    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /** all the students known in the application */
    private List<Player> allPlayers;
    private List<Resource> allResources;
    private List<SolarSystem> allSolarSystems;

    /**
     * Constrcutor of repository
     */
    public Repository() {
        Log.w("[[X]]", "New repo created");
        allPlayers = new ArrayList<>();
        allSolarSystems = new ArrayList<>();
        initResourceList();
    }

    /**
     * initialize the resource list
     */
    private void initResourceList() {
        allResources = new ArrayList<>();
        allResources.add(new Resource("Water", 0, 0, 2,
                30, 3, 4, "DROUGHT", "LOTSOFWATER",
                "DESERT",30, 50));
        allResources.add(new Resource("Furs", 0, 0, 0,
                250, 10, 10, "COLD", "RICHFAUNA",
                "LIFELESS",230, 280));
        allResources.add(new Resource("Food", 1, 0, 1,
                100, 5, 5, "CROPFAIL", "RICHSOIL",
                "POORSOIL",90, 160));
        allResources.add(new Resource("Ore", 2, 2, 3,
                350, 20, 10, "WAR", "MINERALRICH",
                "MINEARALPOOR",350, 420));
        allResources.add(new Resource("Games", 3, 1, 6,
                250, -10, 5, "BOREDOM", "ARTISITC",
                "Never",160, 270));
        allResources.add(new Resource("Firearms", 3, 1, 5,
                1250, -75, 100, "WAR", "WARLIKE",
                "Never",600, 1100));
        allResources.add(new Resource("Medicine", 4, 1, 6,
                650, -20, 10, "PLAGUE", "LOTSOFHERBS",
                "Never",400, 700));
        allResources.add(new Resource("Machines", 4, 3, 5,
                900, -30, 5, "LACKOFWORKERS", "Never",
                "Never",600, 800));
        allResources.add(new Resource("Narcotics", 5, 0, 5,
                3500, -125, 150, "BOREDOM", "WEIRDMUSHROOMS",
                "Never",2000, 3000));
        allResources.add(new Resource("Robots", 6, 4, 7,
                5000, -150, 100, "LACKOFWORKERS", "Never",
                "Never",3500, 5000));
    }

    /**
     * get all methods
     */
    public List<Player> getAllPlayers() { return allPlayers;}
    public List<SolarSystem> getAllSolarSystems() { return allSolarSystems; }
    public List<Resource> getAllResources() { return allResources; }

    /**
     * add a new player
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        allPlayers.add(player);
    }

    /**
     * Update player
     * @param player the player to update
     */
    public void updatePlayer(Player player) {

        allPlayers.set(0, player);
    }

    /**
     * add a new solarSystem
     * @param solarSystem the solar system to add
     * @return whether the solar system has been added
     */
    public boolean addSolarSystem(SolarSystem solarSystem) {
        for (int i = 0; i < allSolarSystems.size(); i++) {
            if (allSolarSystems.get(i).equals(solarSystem)) {
                return false;
            }
        }
        allSolarSystems.add(solarSystem);
        return true;
    }
}