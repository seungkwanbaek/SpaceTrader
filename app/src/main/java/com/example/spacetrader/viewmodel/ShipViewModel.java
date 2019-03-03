package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.model.ShipInteractor;
import com.example.spacetrader.model.Model;


public class ShipViewModel extends AndroidViewModel {
    private ShipInteractor interactor;

    public ShipViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getShipInteractor();
    }

    public Player getPlayer() {
        return interactor.getPlayer();
    }
}
