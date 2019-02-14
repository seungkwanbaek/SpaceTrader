package com.example.spacetrader.model;

import android.util.Log;

import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Student;

public class StudentInteractor extends Interactor {

    public StudentInteractor(Repository repo) {
        super(repo);
    }

    public List<Student> getAllStudents() {
        return getRepository().getAllStudents();
    }

    public void addStudent (Student s) {
        getRepository().addStudent(s);
    }

    public void updateStudent(Student s) {
        getRepository().updateStudent(s);
        Log.d("APP", "Interactor: updating student: " + s);

    }


}
