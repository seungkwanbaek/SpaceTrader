package com.example.spacetrader.model;

import com.example.spacetrader.entities.Player;

public class PlayerInteractor extends Interactor{

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public void addPlayer (Player s) {
        getRepository().addPlayer(s);
    }
}
