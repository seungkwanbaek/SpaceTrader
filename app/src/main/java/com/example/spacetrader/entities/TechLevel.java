package com.example.spacetrader.entities;

public enum TechLevel {
    0 ("Pre-Agriculture"),
    1 ("Agriculture"),
    2 ("Medieval"),
    3 ("Renaissance"),
    4 ("Early Industrial"),
    5 ("Industrial"),
    6 ("Post-Industrial"),
    7 ("Hi-Tech");

    /** the full string representation of the tech levels */
    private final String techlevel;

    /**
     * Constructor for the enumeration
     *
     * @param techlevel  name of the difficulty
     */
    TechLevel(Integer techlevel) {techlevel = techlevel; }

    /**
     *
     * @return   the techlevel
     */
    public String getTechLevel() { return techlevel; }

    /**
     *
     * @return the display string representation of the tech level
     */
    public String toString() { return techlevel; }



}
