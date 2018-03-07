package com.example.project.coursepicker;

import java.io.Serializable;

public class Course implements Serializable {

    private String classDays;
    private String classTime;
    private String description;
    private String name;
    private String prerequisites;
    private int seatsAvail;

    protected Course(String classDays, String classTime, String description, String name,
                  String prerequisites, int seatsAvail){
        this.classDays = classDays;
        this.classTime = classTime;
        this.description = description;
        this.name = name;
        this.prerequisites = prerequisites;
        this.seatsAvail = seatsAvail;
    }

    public String getClassDays() {
        return classDays;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public int getSeatsAvail() {
        return seatsAvail;
    }
}