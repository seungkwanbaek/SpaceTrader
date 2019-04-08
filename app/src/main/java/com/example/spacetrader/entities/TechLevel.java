package com.example.spacetrader.entities;

public enum TechLevel {
    T0 ("Pre-Agriculture"),
    T1 ("Agriculture"),
    T2 ("Medieval"),
    T3 ("Renaissance"),
    T4 ("Early Industrial"),
    T5 ("Industrial"),
    T6 ("Post-Industrial"),
    T7 ("Hi-Tech");

    /** the full string representation of the tech levels */
    private final String techlevel;

    /**
     * Constructor for the enumeration
     * @param Techlevel  name of the techlevel
     */
    TechLevel(String Techlevel) {techlevel = Techlevel; }

    /**
     * Getter method for techLevel
     * @return the techlevel
     */
    public String getTechLevel() { return techlevel; }

    /**
     * toString method
     * @return the display string representation of the tech level
     */
    public String toString() { return techlevel; }



}
