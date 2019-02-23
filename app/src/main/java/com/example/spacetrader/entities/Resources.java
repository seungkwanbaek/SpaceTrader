package com.example.spacetrader.entities;

public enum Resource {
    R0 ("NOSPECIALRESOURCES"),
    R1 ("MINERALRICH"),
    R2 ("MINERALPOOR"),
    R3 ("DESERT"),
    R4 ("LOTSOFWATER"),
    R5 ("RICHSOIL"),
    R6 ("POORSOIL"),
    R7 ("RICHFAUNA"),
    R8 ("LIFELESS"),
    R9 ("WEIRDMUSHROOMS"),
    R10 ("LOTSOFHERBS"),
    R11 ("ARTISTIC"),
    R12 ("WARLIKE");

    /** the full string representation of the resources */
    private final String resource;

    /**
     * Constructor for the enumeration
     *
     * @param Resource  name of the resource
     */
    Resource(String Resource) {
        resource = Resource;
    }

    /**
     *
     * @return   the resource
     */
    public String getResource() { return resource; }

    /**
     *
     * @return the display string representation of the resource
     */
    public String toString() { return resource; }



}
