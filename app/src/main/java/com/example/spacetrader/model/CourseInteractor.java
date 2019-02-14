package com.example.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;

public class CourseInteractor extends Interactor {

    public CourseInteractor(Repository repo) {
        super(repo);

    }

    public void addCourse(Course course) {
        getRepository().addCourse(course);
    }

    public List<Course> getAllCourses() {
        return getRepository().getAllCourses();
    }

    public void deleteCourse(Course course) {
        getRepository().deleteCourse(course);
    }
}
