package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.Model;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerInteractor interactor;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void addPlayer(Player player) {
        interactor.addPlayer(player);
    }

    public Player getPlayer(String name) {
        return interactor.getPlayer(name);
    }
}
