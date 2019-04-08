package com.example.spacetrader.model;

import com.example.spacetrader.entities.Player;
import java.util.List;

public class PlayerInteractor extends Interactor{

    /**
     * Constructor of player interactor
     * @param repo the repository
     */
    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    /**
     * Add player into repository
     * @param p the player
     */
    public void addPlayer(Player p) { getRepository().addPlayer(p); }

    /**
     * Update certain player in repository
     * @param p the player to update
     */
    public void updatePlayer(Player p) { getRepository().updatePlayer(p); }

    /**
     * Getter method for player with certain name
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
