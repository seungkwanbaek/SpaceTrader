package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.PlayerInteractor;

public class CourseViewModel extends AndroidViewModel {
    private CourseInteractor model;
    // private PlayerInteractor model;
    // private List<Player> players;
    private List<Course> courses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getCourseInteractor();
        courses = model.getAllCourses();
    }

    public CourseViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance().getCourseInteractor();
        courses = model.getAllCourses();
    }

    public void addCourse(String courseName, String courseNumber, SchoolCode courseCode) {
        Course course = new Course(courseName, courseNumber, courseCode);
        model.addCourse(course);
        courses = model.getAllCourses();
    }

    public void deleteCourse(Course course) {
        model.deleteCourse(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
