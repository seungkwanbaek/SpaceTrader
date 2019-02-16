package com.example.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the interface to the domain/business classes from anywhere else in the application
 *
 *
 * As it is, we are making this class do everything.
 */

public class Model {

    /** the data repository */
    private Repository myRepository;

    private Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    /** end Singleton Pattern */


    private void registerInteractors() {
        interactorMap.put("Student", new StudentInteractor(myRepository));
        interactorMap.put("Course", new CourseInteractor(myRepository));
        interactorMap.put("Player", new PlayerInteractor(myRepository));

    }

    public CourseInteractor getCourseInteractor() {
        return (CourseInteractor) interactorMap.get("Course");
    }

    public StudentInteractor getStudentInteractor() {
        return (StudentInteractor) interactorMap.get("Student");
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }
}
