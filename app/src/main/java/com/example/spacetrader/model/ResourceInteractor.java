package com.example.spacetrader.model;
import com.example.spacetrader.entities.Resource;

import java.util.List;

public class ResourceInteractor extends Interactor {

    /**
     * Constructor for resource interactor
     * @param repo the repository
     */
    public ResourceInteractor(Repository repo) {
        super(repo);
    }

    /**
     * Get all resources
     * @return the list of resources in repository
     */
    public List<Resource> getAllResource() {
        return getRepository().getAllResources();
    }

    /**
     * Get resource of certain name
     * @param name the player name
     * @return the resource
     */
    public Resource getResource(String name) {
        List<Resource> resources = getAllResource();
        for (Resource r : resources)
            if (r.getName().equals(name))
                return r;
        return null;
    }
}
