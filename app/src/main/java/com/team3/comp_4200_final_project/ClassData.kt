package com.team3.comp_4200_final_project

import java.io.Serializable

// Class to hold information for each class, create time range string from start and end times

class ClassData: Serializable {

    var courseCode: String = ""
    var courseName: String = ""
    var courseStartTime: String = ""
    var courseEndTime: String = ""
    var courseTimeRange: String = ""
    var courseDays: String = ""
    var courseLocation: String = ""
    var courseProfessor: String = ""

    constructor(
        courseCode: String,
        courseName: String,
        courseStartTime: String,
        courseEndTime: String,
        courseDays: String,
        courseLocation: String,
        courseProfessor: String,
    ) {
        this.courseCode = courseCode
        this.courseName = courseName
        courseTimeRange = "$courseStartTime - $courseEndTime"
        this.courseDays = courseDays
        this.courseLocation = courseLocation
        this.courseProfessor = courseProfessor
    }

    constructor(
        courseCode: String,
        courseName: String,
        courseTimeRange: String,
        courseDays: String,
        courseLocation: String,
        courseProfessor: String,
    ) {
        this.courseCode = courseCode
        this.courseName = courseName
        this.courseTimeRange = courseTimeRange
        this.courseDays = courseDays
        this.courseLocation = courseLocation
        this.courseProfessor = courseProfessor
    }
}