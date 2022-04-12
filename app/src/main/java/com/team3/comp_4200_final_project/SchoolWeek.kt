package com.team3.comp_4200_final_project

import android.util.Log
import com.team3.comp_4200_final_project.db.Course
import java.io.Serializable

// Class to hold all classes for each day of the week, implements Serializable so it can be passed
// to Fragments using Bundles

class SchoolWeek : Serializable {

    // HashMap to hold the ArrayLists of ClassData
    val weekHashMap: HashMap<String, ArrayList<Course>> = HashMap()

    // Initialize days of the week
    init{
        weekHashMap["Monday"] = ArrayList()
        weekHashMap["Tuesday"] = ArrayList()
        weekHashMap["Wednesday"] = ArrayList()
        weekHashMap["Thursday"] = ArrayList()
        weekHashMap["Friday"] = ArrayList()
    }

    // Function to add classes to the ArrayLists, parse the days and add class to each day it occurs
    fun addClass(course: Course) {
        val daysArr = course.courseDays.split(", ")
        for (k in daysArr) {
            weekHashMap[k]?.add(course)
            weekHashMap[k]?.sortBy { it.courseTimeRange }
        }
    }
}
