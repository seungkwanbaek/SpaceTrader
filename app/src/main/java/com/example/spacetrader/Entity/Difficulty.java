package com.example.spacetrader.Entity;

/**
 * Created by bryanbaek on 2/17/19
 *
 * This class represents the various difficulty levels for players
 */
public enum Difficulty {
    Beginner ("Beginner"),
    Easy ("Easy"),
    Normal ("Normal"),
    Hard ("Hard"),
    Impossible ("Impossible");

    /** the full string representation of the difficulties */
    private final String difficulty;

    /**
     * Constructor for the enumeration
     *
     * @param difficulty   full name of the course
     */
    Difficulty(String Difficulty) {
        difficulty = Difficulty;
    }

    /**
     *
     * @return   the full difficulty
     */
    public String getDifficulty() { return difficulty; }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return difficulty; }



}
