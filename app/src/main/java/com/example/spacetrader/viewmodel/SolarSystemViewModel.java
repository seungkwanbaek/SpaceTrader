package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.SolarSystemInteractor;

import java.util.List;

public class SolarSystemViewModel extends AndroidViewModel {
    private SolarSystemInteractor interactor;

    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    public void addSolarSystem(SolarSystem solarSystem) {
        interactor.addSolarSystem(solarSystem);
    }

    public SolarSystem getSolarSystem(String name) {
        return interactor.getSolarSystem(name);
    }

    public List<SolarSystem> getAllSolarSystems() {
        return interactor.getAllSolarSystems();
    }
}
