package com.example.spacetrader.entities;

public enum Resources {
    Beginner ("Beginner"),
    Easy ("Easy"),
    Normal ("Normal"),
    Hard ("Hard"),
    Impossible ("Impossible");

    /** the full string representation of the difficulties */
    private final String resource;

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
