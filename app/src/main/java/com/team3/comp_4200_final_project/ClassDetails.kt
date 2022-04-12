package com.team3.comp_4200_final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ClassDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        val courseName = intent.getStringExtra("courseName")?:""
        val courseCode = intent.getStringExtra("courseCode")?:""
        val profName = intent.getStringExtra("profName")?:""
        val courseTimes = intent.getStringExtra("timeRange")?:""
        val courseDays = intent.getStringExtra("courseDays")?:""
        val courseLocation = intent.getStringExtra("courseLocation")?:""

        val course_name_details: TextView = findViewById(R.id.course_name_details)
        val course_prof: TextView = findViewById(R.id.prof)
        val course_times: TextView = findViewById(R.id.times)
        val course_days: TextView = findViewById(R.id.days)
        val course_location: TextView = findViewById(R.id.location)

        val nameCode = courseCode + "\n\n" + courseName
        course_name_details.text = nameCode

        val professorName = "Professor: " + profName
        course_prof.text = professorName

        val classDays = "Class held on: " + courseDays
        course_days.text = classDays

        val classTimes = "Time of lecture: " + courseTimes
        course_times.text = classTimes

        val classLocation = "Location: " + courseLocation
        course_location.text = classLocation


    }
}