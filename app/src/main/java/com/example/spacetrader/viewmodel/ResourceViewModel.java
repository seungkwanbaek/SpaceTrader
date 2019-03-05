package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.ResourceInteractor;

import java.util.List;

public class ResourceViewModel extends AndroidViewModel {
    private ResourceInteractor interactor;

    public ResourceViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getResourceInteractor();
    }

    public Resource getResource(String name) {
        return interactor.getResource(name);
    }
    public List<Resource> getAllResource() { return interactor.getAllResource(); }
}
