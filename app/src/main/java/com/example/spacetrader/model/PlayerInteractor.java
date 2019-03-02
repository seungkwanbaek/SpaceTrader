package com.example.spacetrader.model;

import com.example.spacetrader.entities.Player;
import java.util.List;

public class PlayerInteractor extends Interactor{

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public void addPlayer(Player s) {
        getRepository().addPlayer(s);
    }

    public Player getPlayer(String name) {
        List<Player> players = getRepository().getAllPlayers();

        for (Player p : players) {
            if (p.getUserName().equals(name))
                return p;
        }

        return null;
    }
}
