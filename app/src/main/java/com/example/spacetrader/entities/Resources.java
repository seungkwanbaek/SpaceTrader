package com.example.spacetrader.entities;

public enum Resources {
    0 ("NOSPECIALRESOURCES"),
    1 ("MINERALRICH"),
    2 ("MINERALPOOR"),
    3 ("DESERT"),
    4 ("LOTSOFWATER"),
    5 ("RICHSOIL"),
    6 ("POORSOIL"),
    7 ("RICHFAUNA"),
    8 ("LIFELESS"),
    9 ("WEIRDMUSHROOMS"),
    10 ("LOTSOFHERBS"),
    11 ("ARTISTIC"),
    12 ("WARLIKE");

    /** the full string representation of the resources */
    private final String resource;

    /**
     * Constructor for the enumeration
     *
     * @param Resource  name of the resource
     */
    Resource(Integer Resource) {
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
