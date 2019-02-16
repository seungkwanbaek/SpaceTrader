package com.example.spacetrader.model;

import android.util.Log;

import java.util.List;

import com.example.spacetrader.Entity.Player;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

//    public List<Player> getAllPlayers() {
//        return getRepository().getAllPlayers();
//    }

    public void addPlayer (Player s) {
        getRepository().addPlayer(s);
    }

//    public void updatePlayer(Player s) {
//        getRepository().updatePlayer(s);
//        Log.d("APP", "Interactor: updating player: " + s);
//    }


}
