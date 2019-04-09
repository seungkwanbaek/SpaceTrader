package com.example.spacetrader.model;

/**
 * THe Model Class
 */
public final class Model {
    private Repository repo;
    private PlayerInteractor playerInteractor;
    private SolarSystemInteractor solarSystemInteractor;
    private ResourceInteractor resourceInteractor;
    private ShipInteractor shipInteractor;

    private static Model instance = new Model();

    /**
     * get instance of the model
     * @return the instance of model
     */
    public static Model getInstance() { return instance; }

    /**
     * Constructor for Model
     */
    private Model() {
        repo = new Repository();
        playerInteractor = new PlayerInteractor(repo);
        solarSystemInteractor = new SolarSystemInteractor(repo);
        resourceInteractor = new ResourceInteractor(repo);
        shipInteractor = new ShipInteractor(repo);
    }

    /**
     * Getter for ship interactor
     * @return ship interactor
     */
    public ShipInteractor getShipInteractor() { return shipInteractor; }

    /**
     * Getter for player interactor
     * @return player interactor
     */
    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

    /**
     * Getter for solar system interactor
     * @return solarSystem interactor
     */
    public SolarSystemInteractor getSolarSystemInteractor() {
        return solarSystemInteractor;
    }

    /**
     * Getter for resource interactor
     * @return resource interactor
     */
    public ResourceInteractor getResourceInteractor() {
        return resourceInteractor;
    }

}
