package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.PlayerInteractor;


public class ConfigurationViewModel extends AndroidViewModel {
    private PlayerInteractor model;
    //private List<Player> players;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getPlayerInteractor();
        //players = model.getAllPlayers();
    }

    public void addPlayer(String playerName, Difficulty difficulty) {
        Player player = new Player(playerName, difficulty);
        model.addPlayer(player);
        //players = model.getAllPlayers();
    }

//    public void deletePlayer(Player player) {
//        model.deletePlayer(player);
//    }
//
//    public List<Player> getPlayers() {
//        return players;
//    }

}
