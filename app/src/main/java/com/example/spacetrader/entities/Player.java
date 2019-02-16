package com.example.spacetrader.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String user_name;

    private String difficulty;

    private HashMap<String, Integer> skill_points = new HashMap<String, Integer>();

    private String ship_type;

    private int playerID;

    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_) {
        this(user_name_, difficulty_, skill_points_, "Gnat");
    }

    public Player(String user_name_, String difficulty_, ArrayList<Integer> skill_points_, String ship_type_) {
        user_name = user_name_;
        difficulty = difficulty_;
        ship_type = ship_type_;
        skill_points.put("Pilot", skill_points_.get(0));
        skill_points.put("Fighter", skill_points_.get(1));
        skill_points.put("Trade", skill_points_.get(2));
        skill_points.put("Engineer", skill_points_.get(3));
    }

    public String getUserName() { return user_name; }

    public String getDifficulty() { return difficulty; }

    public Integer getSkillPoint(String skill_name) { return skill_points.get(skill_name); }

    public HashMap getSkillPoints() { return skill_points; }

    public String getShipType() { return ship_type; }

    public int getPlayerID() { return playerID; }

    /**
     * Setter for user_name
     * @param user_name the user name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Setter for difficulty
     * @param difficulty the difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Setter for skill_name
     * @param skill_name the skill name
     * @param point the point
     */
    public void setSkill_points(HashMap<String, Integer> skill_points) {
        for (String key : skill_points.keySet()) {
            this.skill_points.put(key, skill_points.get(key));
        }
    }

    /**
     * Setter for ship_type
     * @param ship_type the ship_type
     */
    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public void setPlayerID(int id) {
        this.playerID = id;
    }

}