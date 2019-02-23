package com.example.spacetrader.model;
import com.example.spacetrader.entities.SolarSystem;

import java.util.List;

public class SolarSystemInteractor extends Interactor {
    private Repository myRepository;

    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    public boolean addSolarSystem (SolarSystem s) {
        return getRepository().addSolarSystem(s);
    }

    protected Repository getRepository() {
        return myRepository;
    }

    public List<SolarSystem> getAllSolarSystems() { return myRepository.getAllSolarSystems(); }

}
