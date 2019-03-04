package com.example.spacetrader.model;

import com.example.spacetrader.entities.Player;
import java.util.List;

public class ShipInteractor extends Interactor{
    public ShipInteractor(Repository repo) {
        super(repo);
    }

    public Player getPlayer(String name) {
        List<Player> players = getRepository().getAllPlayers();
        for (Player p : players)
            if (p.getUserName().equals(name))
                return p;
        return null;
    }

}
