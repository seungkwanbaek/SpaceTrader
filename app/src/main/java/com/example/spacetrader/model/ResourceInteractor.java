package com.example.spacetrader.model;
import com.example.spacetrader.entities.Resource;

import java.util.List;

public class ResourceInteractor extends Interactor {
    public ResourceInteractor(Repository repo) {
        super(repo);
    }

    public List<Resource> getAllResource() { return getRepository().getAllResources(); }

    public Resource getResource(String name) {
        List<Resource> resources = getAllResource();
        for (Resource r : resources)
            if (r.getName().equals(name))
                return r;
        return null;
    }
}
