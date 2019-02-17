package com.example.spacetrader.model;

public class Model {
    private Repository repo;
    private PlayerInteractor playerInteractor;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        repo = new Repository();
        playerInteractor = new PlayerInteractor(repo);
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }
}
