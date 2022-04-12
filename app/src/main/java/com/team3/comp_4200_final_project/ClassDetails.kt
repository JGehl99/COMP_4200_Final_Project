package com.team3.comp_4200_final_project

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClassDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        //Get values from intent
        val courseName = intent.getStringExtra("courseName") ?: ""
        val courseCode = intent.getStringExtra("courseCode") ?: ""
        val profName = intent.getStringExtra("profName") ?: ""
        val courseTimes = intent.getStringExtra("timeRange") ?: ""
        val courseDays = intent.getStringExtra("courseDays") ?: ""
        val courseLocation = intent.getStringExtra("courseLocation") ?: ""
        val week = intent.getSerializableExtra("week") as SchoolWeek

        //Get and assign the corresponding textViews
        val course_name_details: TextView = findViewById(R.id.course_name_details)
        val course_prof: TextView = findViewById(R.id.prof)
        val course_times: TextView = findViewById(R.id.times)
        val course_days: TextView = findViewById(R.id.days)
        val course_location: TextView = findViewById(R.id.location)
        val add_btn: Button = findViewById(R.id.add_to_cal)

        //Set all the information about the course
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

        add_btn.setOnClickListener {
            week.addClass(
                ClassData(
                    courseCode,
                    courseName,
                    courseTimes,
                    courseDays,
                    courseLocation,
                    profName
                )
            )
            finish()
        }

    }
}