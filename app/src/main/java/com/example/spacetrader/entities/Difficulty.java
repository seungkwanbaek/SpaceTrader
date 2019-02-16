package com.example.spacetrader.entities;

/**
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
     * @param Difficulty  name of the difficulty
     */
    Difficulty(String Difficulty) {
        difficulty = Difficulty;
    }

    /**
     *
     * @return   the difficulty
     */
    public String getDifficulty() { return difficulty; }

    /**
     *
     * @return the display string representation of the difficulty
     */
    public String toString() { return difficulty; }



}