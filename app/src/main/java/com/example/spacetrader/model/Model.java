package com.example.spacetrader.model;

public class Model {
    private Repository repo;
    private PlayerInteractor playerInteractor;
    private SolarSystemInteractor solarSystemInteractor;
    private ResourceInteractor resourceInteractor;
    private ShipInteractor shipInteractor;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        repo = new Repository();
        playerInteractor = new PlayerInteractor(repo);
        solarSystemInteractor = new SolarSystemInteractor(repo);
        resourceInteractor = new ResourceInteractor(repo);
        shipInteractor = new ShipInteractor(repo);
    }

    public ShipInteractor getShipInteractor() { return shipInteractor; }
    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }
    public SolarSystemInteractor getSolarSystemInteractor() { return solarSystemInteractor; }
    public ResourceInteractor getResourceInteractor() { return resourceInteractor; }

}
