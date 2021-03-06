package com.example.project.coursepicker;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Brianna on 2018-02-23.
 */

public class User implements Serializable {

    public String Password;
    public Course Courses;

    public User() {}

    public User(String Password) {
        this.Password = Password;
    }

    public User(Course course) {
        this.Courses = course;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Password", Password);
        result.put("Courses", Courses);

        return result;
    }

}
