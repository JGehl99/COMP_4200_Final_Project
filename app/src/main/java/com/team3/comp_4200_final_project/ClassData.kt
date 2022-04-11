package com.team3.comp_4200_final_project

import java.io.Serializable

// Class to hold information for each class, create time range string from start and end times

class ClassData(
    val courseCode: String,
    val courseName: String,
    courseStartTime: String,
    courseEndTime: String,
    var courseDays: String,
    val courseLocation: String,
    val courseProfessor: String,
): Serializable {
    val courseTimeRange = "$courseStartTime - $courseEndTime"
}
