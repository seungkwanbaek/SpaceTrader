package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.example.spacetrader.model.ShipInteractor;
import com.example.spacetrader.model.Model;

/**
 * The ShipViewModel class
 */
public class ShipViewModel extends AndroidViewModel {
    private ShipInteractor interactor;

    /**
     * Constructor of ShipViewModel
     * @param application the application
     */
    public ShipViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getShipInteractor();
    }
}
