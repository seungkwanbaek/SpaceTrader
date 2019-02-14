package com.example.spacetrader.Entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


/**
 * This class represents a single Student in the database.   It is an information holder.
 *
 *
 */

public class Student implements Serializable {
    /** a demonstration of using something other than an enum for holding choices
     *  IRL I would always use an enumeration for this
     */
    public static List<String> legalMajors = Arrays.asList("CS", "CM", "ISYE", "MATH", "EE", "CMPE", "NA");
    public static List<String> legalClasses = Arrays.asList("FRESHMAN", "SOPHOMORE", "JUNIOR", "SENIOR");

    /** a globally unique number for this object */
    private int id;

    /** this students name */
    private String name;

    /** this students major */
    private String major;

    /** this students class standing */
    private ClassStanding classStanding;

    /**
     * Lookup a major based on its code.  Returns the postion of that
     * major in the array, would not have to do this for an enum
     *
     * @param code the major to find
     *
     * @return the index of the array that corresponds to the submitted major
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalMajors.size()) {
            if (code.equals(legalMajors.get(i))) return i;
            ++i;
        }
        return 0;
    }


    /**
     * Constructor required for making the  new object
     *
     * @param nam     name of the student
     * @param maj    major of the student
     */
    public Student(String nam, String maj) {
        this(nam, maj, ClassStanding.FRESHMAN);
    }

    /**
     * Constructor required for making the new object
     *
     * @param nam     name of the student
     * @param maj    major of the student
     * @param cs     class standing of the student
     */
    public Student(String nam, String maj, ClassStanding cs) {
        name = nam;
        major = maj;
        classStanding = cs;
    }

    //Getters are required for accessing the fields from the database

    public int getId() {
        return id;
    }

    public String get_name() {
        return name;
    }

    public String get_major() {
        return major;
    }

    public ClassStanding get_classStanding() {
        return this.classStanding;
    }

    public void setName(String nam) { name = nam; }

    public void setMajor(String maj) {major = maj; }

    public void setClassStanding(ClassStanding cs) {this.classStanding = cs; }

    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student " + name + ", major: " + major + ", id: " + id
                + ", class standing: " + classStanding;
    }

}
