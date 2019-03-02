package com.example.spacetrader.model;
import com.example.spacetrader.entities.SolarSystem;

import java.util.List;

public class SolarSystemInteractor extends Interactor {

    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    public boolean addSolarSystem (SolarSystem s) {
        return getRepository().addSolarSystem(s);
    }

    public List<SolarSystem> getAllSolarSystems() { return getRepository().getAllSolarSystems(); }

    public SolarSystem getSolarSystem(String name) {
        List<SolarSystem> allSolarSystems = getAllSolarSystems();
        for (SolarSystem s : allSolarSystems)
            if (s.getName().equals(name))
                return s;
        return null;
    }
}
