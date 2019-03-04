package com.example.spacetrader.model;

import android.util.Log;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class Repository {

    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /** all the students known in the application */
    private List<Player> allPlayers;

    private List<SolarSystem> allSolarSystems;

    public Repository() {
        Log.w("[[X]]", "New repo created");
        allPlayers = new ArrayList<>();
        allSolarSystems = new ArrayList<>();
    }

    /**
     * get all players
     * @return list of all players
     */
    public List<Player> getAllPlayers() { return allPlayers;}

    /**
     * get all solarSystems
     * @return set of all solar systems
     */
    public List<SolarSystem> getAllSolarSystems() { return allSolarSystems; }


    /**
     * add a new player
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        //player.setPlayerID(Repository.getNextUniqueID());
        allPlayers.add(player);
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


    /*public void updatePlayer(Player p) {
        for (Player player: allPlayers) {
            if (player.getPlayerID() == p.getPlayerID()) {
                Log.d("APP", "Found player to update: " + player);
                player.setDifficulty(p.getDifficulty());
                player.setUser_name(p.getUserName());
                player.setSkill_points(p.getSkillPoints());
                Log.d("APP", "Updated list: " + allPlayers.toString());
                return;
            }
        }
        Log.d("APP", "Student not found with id = " + p.getPlayerID());

    }*/
}