package com.example.spacetrader.Entity;


import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String user_name;

    private String difficulty;

    private HashMap<String, Integer> skill_points = new HashMap<String, Integer>();

    private String ship_type;

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

    public String getShipType() { return ship_type; }

}