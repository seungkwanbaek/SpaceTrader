package com.example.spacetrader.entities;

import java.util.ArrayList;
import java.util.List;

public final class ResourcesList {
    private List<Resource> resourceList;
    public ResourcesList() {
        resourceList = new ArrayList<>();
        resourceList.add(new Resource("Water", 0, 0, 2,
                30, 3, 4, "DROUGHT", "LOTSOFWATER",
                "DESERT",30, 50));
        resourceList.add(new Resource("Furs", 0, 0, 0,
                        250, 10, 10, "COLD", "RICHFAUNA",
                        "LIFELESS",230, 280));
        resourceList.add(new Resource("Food", 1, 0, 1,
                        100, 5, 5, "CROPFAIL", "RICHSOIL",
                        "POORSOIL",90, 160));
        resourceList.add(new Resource("Ore", 2, 2, 3,
                350, 20, 10, "WAR", "MINERALRICH",
                "MINEARALPOOR",350, 420));
        resourceList.add(new Resource("Games", 3, 1, 6,
                250, -10, 5, "BOREDOM", "ARTISITC",
                "Never",160, 270));
        resourceList.add(new Resource("Firearms", 3, 1, 5,
                1250, -75, 100, "WAR", "WARLIKE",
                "Never",600, 1100));
        resourceList.add(new Resource("Medicine", 4, 1, 6,
                650, -20, 10, "PLAGUE", "LOTSOFHERBS",
                "Never",400, 700));
        resourceList.add(new Resource("Machines", 4, 3, 5,
                900, -30, 5, "LACKOFWORKERS", "Never",
                "Never",600, 800));
        resourceList.add(new Resource("Narcotics", 5, 0, 5,
                3500, -125, 150, "BOREDOM", "WEIRDMUSHROOMS",
                "Never",2000, 3000));
        resourceList.add(new Resource("Robots", 6, 4, 7,
                5000, -150, 100, "LACKOFWORKERS", "Never",
                "Never",3500, 5000));
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }
}
