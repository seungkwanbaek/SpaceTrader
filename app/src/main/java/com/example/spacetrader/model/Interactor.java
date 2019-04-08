package com.example.spacetrader.model;

public class Interactor {
    private Repository myRepository;

    /**
     * Constructor for Interactor
     * @param repo the repository
     */
    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    /**
     * Getter method for repository
     * @return the repository
     */
    protected Repository getRepository() {
        return myRepository;
    }
}
