package com.example.spacetrader.model;

import com.example.spacetrader.entities.SolarSystem;

public class Model {
    private Repository repo;
    private PlayerInteractor playerInteractor;
    private SolarSystemInteractor solarSystemInteractor;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        repo = new Repository();
        playerInteractor = new PlayerInteractor(repo);
        solarSystemInteractor = new SolarSystemInteractor(repo);
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }
    public SolarSystemInteractor getSolarSystemInteractor() { return solarSystemInteractor; }
}
