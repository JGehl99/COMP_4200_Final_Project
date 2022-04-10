package com.team3.comp_4200_final_project

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

    // Function to parse passed in classDays string (formatted like: MWF, TWF, MTH, etc.)
    private fun parseDays(str_: String): ArrayList<String>{
        var str = str_
        val arr = ArrayList<String>()

        // Loop until str is empty, read first char, add day to list, then drop first char
        // Special cases for T and TH since TH is the only one with two chars
        while (str != ""){
            if (str[0] == 'M') {
                arr.add("Monday")
                str = str.drop(1)
            }
            else if (str[0] == 'T' && str[1] != 'H') {
                arr.add("Tuesday")
                str = str.drop(1)
            }
            else if (str[0] == 'W') {
                arr.add("Wednesday")
                str = str.drop(1)
            }
            else if (str[0] == 'T' && str[1] == 'H') {
                arr.add("Thursday")
                str = str.drop(2)
            }
            else if (str[0] == 'F') {
                arr.add("Friday")
                str = str.drop(1)
            }
        }
        return arr
    }
}
