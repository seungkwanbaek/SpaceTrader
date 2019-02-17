package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.Model;

public class AddPlayerViewModel extends AndroidViewModel {
    private PlayerInteractor interactor;
    private Player currentPlayer;

    public AddPlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void addPlayer(Player player) {
        interactor.addPlayer(player);
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

}
