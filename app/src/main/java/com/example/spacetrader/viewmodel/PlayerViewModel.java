package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.Model;

/**
 * The PlayerViewModel class
 */
public class PlayerViewModel extends AndroidViewModel {
    private PlayerInteractor interactor;

    /**
     * Constructor of PlayerViewModel
     * @param application the application
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * Add a player to model
     * @param p the player to add
     */
    public void addPlayer(Player p) {
        interactor.addPlayer(p);
    }

    /**
     * Retrieve a player object from model by its name
     * @param name player's name
     * @return the player object
     */
    public Player getPlayer(String name) {
        return interactor.getPlayer(name);
    }

    /**
     * Update the player information in model
     * @param p the player object to update
     */
    public void setPlayer(Player p) { interactor.updatePlayer(p); }
}
