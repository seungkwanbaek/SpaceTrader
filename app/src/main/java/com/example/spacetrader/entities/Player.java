package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Serializable {
    private String user_name;

    private String difficulty;

    private HashMap<String, Integer> skill_points = new HashMap<String, Integer>();

    private String ship_type;

    private int playerID;

    private SolarSystem solarSystem;

    public Player(Player player) {
        this.user_name = player.user_name;
        this.difficulty = player.difficulty;
        this.skill_points.put("Pilot", player.skill_points.get("Pilot"));
        this.skill_points.put("Fighter", player.skill_points.get("Fighter"));
        this.skill_points.put("Trader", player.skill_points.get("Trader"));
        this.skill_points.put("Trader", player.skill_points.get("Trader"));
        this.solarSystem = player.solarSystem;
    }

    public Player(String user_name_, String difficulty_,
                  ArrayList<Integer> skill_points_, SolarSystem solarSystem) {
        this(user_name_, difficulty_, skill_points_, "Gnat", solarSystem);
    }

    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_,
                  String ship_type_, SolarSystem solarSystem) {
        user_name = user_name_;
        difficulty = difficulty_;
        ship_type = ship_type_;
        skill_points.put("Pilot", skill_points_.get(0));
        skill_points.put("Fighter", skill_points_.get(1));
        skill_points.put("Trader", skill_points_.get(2));
        skill_points.put("Engineer", skill_points_.get(3));
        this.solarSystem = solarSystem;
    }

    public String getUserName() { return user_name; }

    public String getDifficulty() { return difficulty; }

    public Integer getSkillPoint(String skill_name) { return skill_points.get(skill_name); }

    public HashMap getSkillPoints() { return skill_points; }

    public String getShipType() { return ship_type; }

    public int getPlayerID() { return playerID; }

    public SolarSystem getSolarSystem() { return solarSystem; }



    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setSkill_points(HashMap<String, Integer> skill_points) {
        for (String key : skill_points.keySet()) {
            this.skill_points.put(key, skill_points.get(key));
        }
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public void setPlayerID(int id) {
        this.playerID = id;
    }

    public void setSolarSystem(SolarSystem solarSystem) { this.solarSystem = solarSystem; }


}