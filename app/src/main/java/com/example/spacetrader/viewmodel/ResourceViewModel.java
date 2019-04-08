package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.ResourceInteractor;

import java.util.List;

public class ResourceViewModel extends AndroidViewModel {
    private ResourceInteractor interactor;

    /**
     * Constructor of ResourceViewModel
     * @param application
     */
    public ResourceViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getResourceInteractor();
    }

    /**
     * Get Resource by its name
     * @param name the name of Resource to get
     * @return the Resource with the input name
     */
    public Resource getResource(String name) {
        return interactor.getResource(name);
    }

    /**
     * Return a list of all Resources
     * @return list of all Resources
     */
    public List<Resource> getAllResource() { return interactor.getAllResource(); }
}
