package com.example.spacetrader.model;

import android.util.Log;

import com.example.spacetrader.Entity.Player;

import java.util.ArrayList;
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


    public Repository() {
        allPlayers = new ArrayList<>();
//        loadDummyData();
    }


    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when persistence functionality is present.
     */
//    private void loadDummyData() {
//        addCourse(new Course("Objects and Design", "2340", SchoolCode.CS));
//        addCourse(new Course( "TQM", "4321", SchoolCode.IE));
//        addCourse(new Course("Concrete Ideas", "5432", SchoolCode.AR));
//        addCourse(new Course("Calc I", "2213", SchoolCode.MATH));
//        addStudent(new Student("Bob", "CS"));
//        addStudent(new Student("Sally", "ISYE"));
//        addStudent(new Student("Fred", "Math"));
//        addStudent(new Student("Edith", "CM"));
//        allCourses.get(0).registerStudent(allStudents.get(0));
//        allCourses.get(0).registerStudent(allStudents.get(1));
//        allCourses.get(1).registerStudent(allStudents.get(3));
//        allCourses.get(1).registerStudent(allStudents.get(2));
//    }

    /**
     * get all players
     * @return list of all players
     */
    public List<Player> getAllPlayers() { return allPlayers;}


    /**
     * add a new player
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        player.setPlayerID(Repository.getNextUniqueID());
        allPlayers.add(player);
    }

    public void deletePlayer(Player player) {
        allPlayers.remove(player);
    }

    public void updatePlayer(Player p) {
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

    }
}
