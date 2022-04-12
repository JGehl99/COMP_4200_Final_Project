package com.team3.comp_4200_final_project

import android.util.Log
import java.io.Serializable

// Class to hold all classes for each day of the week, implements Serializable so it can be passed
// to Fragments using Bundles

class SchoolWeek : Serializable {

    // HashMap to hold the ArrayLists of ClassData
    val weekHashMap: HashMap<String, ArrayList<ClassData>> = HashMap()

    // Initialize days of the week
    init{
        weekHashMap["Monday"] = ArrayList()
        weekHashMap["Tuesday"] = ArrayList()
        weekHashMap["Wednesday"] = ArrayList()
        weekHashMap["Thursday"] = ArrayList()
        weekHashMap["Friday"] = ArrayList()
    }

    // Function to add classes to the ArrayLists, parse the days and add class to each day it occurs
    fun addClass(classData: ClassData) {
        val daysArr = parseDays(classData.courseDays)
        classData.courseDays = daysArr.joinToString("/")
        for (k in daysArr) {
            weekHashMap[k]?.add(classData)
            weekHashMap[k]?.sortBy { it.courseTimeRange }
        }
    }


}

// Function to parse passed in classDays string (formatted like: MWF, TWF, MTH, etc.)
fun parseDays(str_: String): ArrayList<String>{
    var str = str_
    val arr = ArrayList<String>()

    // Loop until str is empty, read first char, add day to list, then drop first char
    // Special cases for T and TH since TH is the only one with two chars
    while (str.isNotEmpty()){
        if (str[0] == 'M') {
            arr.add("Monday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'W') {
            arr.add("Wednesday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'F') {
            arr.add("Friday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'T') {
            if (str.length > 1 && str[1] == 'H') {
                arr.add("Thursday")
                str = str.drop(2)
                continue
            }
            arr.add("Tuesday")
            str = str.drop(1)
            continue
        }
        Log.e("TAG_", "String does not match any letters! $str")
        break
    }
    return arr
}
