package com.example.spacetrader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.entity.Course;
import edu.gatech.cs2340.lab3newcomponents.entity.SchoolCode;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;

/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class Repository {

    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /** all the students known in the application */
    private List<Student> allStudents;

    /** all the courses known in the application */
    private List<Course> allCourses;

    public Repository() {
        allCourses = new ArrayList<>();
        allStudents = new ArrayList<>();
        loadDummyData();
    }


    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when persistence functionality is present.
     */
    private void loadDummyData() {
        addCourse(new Course("Objects and Design", "2340", SchoolCode.CS));
        addCourse(new Course( "TQM", "4321", SchoolCode.IE));
        addCourse(new Course("Concrete Ideas", "5432", SchoolCode.AR));
        addCourse(new Course("Calc I", "2213", SchoolCode.MATH));
        addStudent(new Student("Bob", "CS"));
        addStudent(new Student("Sally", "ISYE"));
        addStudent(new Student("Fred", "Math"));
        addStudent(new Student("Edith", "CM"));
        allCourses.get(0).registerStudent(allStudents.get(0));
        allCourses.get(0).registerStudent(allStudents.get(1));
        allCourses.get(1).registerStudent(allStudents.get(3));
        allCourses.get(1).registerStudent(allStudents.get(2));
    }

    /**
     * get all the students in teh system
     * @return list of all students
     */
    public List<Student> getAllStudents() { return allStudents;}

    /**
     * get all the courses in the system
     * @return list of all the courses
     */
    public List<Course>  getAllCourses() { return allCourses; }

    public List<Student> getStudentsForCourse(Course course) {
        return course.getRegisteredStudents();
    }

    /**
     * add a new course
     * @param course the course to add
     */
    public void addCourse(Course course) {
        course.setId(Repository.getNextUniqueID());
        allCourses.add(course);
    }

    /** add a new student to the system
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        student.setId(Repository.getNextUniqueID());
        allStudents.add(student);
    }


    public void deleteCourse(Course course) {
        allCourses.remove(course);
    }

    public void updateStudent(Student s) {
        for (Student student: allStudents) {
            if (student.getId() == s.getId()) {
                Log.d("APP", "Found student to update: " + student);
                student.setMajor(s.get_major());
                student.setName(s.get_name());
                student.setClassStanding(s.get_classStanding());
                Log.d("APP", "Updated list: " + allStudents.toString());
                return;
            }
        }
        Log.d("APP", "Student not found with id = " + s.getId());

    }
}
