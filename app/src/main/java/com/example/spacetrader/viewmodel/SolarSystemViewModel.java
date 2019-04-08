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

    /**
     * Constructor of SolarSystemViewModel
     * @param application
     */
    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    /**
     * Add a solarSystem to model
     * @param solarSystem the solarSystem to add
     */
    public void addSolarSystem(SolarSystem solarSystem) {
        interactor.addSolarSystem(solarSystem);
    }

    /**
     * Get the solarSystem object by name
     * @param name the name of the target solarSystem
     * @return the solarSystem object with specified name
     */
    public SolarSystem getSolarSystem(String name) {
        return interactor.getSolarSystem(name);
    }

    /**
     * Return a list of all solarSystem in model
     * @return the list of all solarSystem in model
     */
    public List<SolarSystem> getAllSolarSystems() {
        return interactor.getAllSolarSystems();
    }
}
