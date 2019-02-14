package com.example.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;
import edu.gatech.cs2340.lab3newcomponents.model.Model;
import edu.gatech.cs2340.lab3newcomponents.model.StudentInteractor;

public class StudentListingViewModel extends AndroidViewModel {

    private StudentInteractor interactor;
    private Course currentCourse;

    public StudentListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getStudentInteractor();
    }

    public void setCurrentCourse(Course course) {
        currentCourse = course;
    }

    public List<Student> getRegisteredStudents() {
        Log.d("APP", "Current Course" + currentCourse);
        return currentCourse.getRegisteredStudents();
    }

    public List<Student> getStudents() {
        return interactor.getAllStudents();
    }



}
