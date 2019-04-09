package com.example.spacetrader.model;
import com.example.spacetrader.entities.SolarSystem;

import java.util.List;

/**
 * The SolarSystemInteractor class
 */
public class SolarSystemInteractor extends Interactor {

    /**
     * Constructor of the solar system interactor
     * @param repo the repository
     */
    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    /**
     * Add solar system into repository
     * @param s the solar system
     * @return the result of this operation
     */
    public boolean addSolarSystem (SolarSystem s) {
        return getRepository().addSolarSystem(s);
    }

    /**
     * Get all solar system
     * @return the list of all solar systems
     */
    public List<SolarSystem> getAllSolarSystems() { return getRepository().getAllSolarSystems(); }

    /**
     * Get certain solar system with name
     * @param name the solar system name
     * @return the solar system
     */
    public SolarSystem getSolarSystem(String name) {
        List<SolarSystem> allSolarSystems = getAllSolarSystems();
        for (SolarSystem s : allSolarSystems)
            if (s.getName().equals(name))
                return s;
        return null;
    }
}
