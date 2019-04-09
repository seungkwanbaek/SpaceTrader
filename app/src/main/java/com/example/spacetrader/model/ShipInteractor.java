package com.example.spacetrader.model;

import com.example.spacetrader.entities.Player;
import java.util.List;

/**
 * The ShipInteractor class
 */
public class ShipInteractor extends Interactor{

    /**
     * Constructor for ship interactor
     * @param repo the repository
     */
    public ShipInteractor(Repository repo) {
        super(repo);
    }

    /**
     * Get player with certain name
     * @param name the player name
     * @return the player
     */
    public Player getPlayer(String name) {
        List<Player> players = getRepository().getAllPlayers();
        for (Player p : players)
            if (p.getUserName().equals(name))
                return p;
        return null;
    }

}
