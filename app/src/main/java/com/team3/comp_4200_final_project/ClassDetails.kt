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

        val course_name_details: TextView = findViewById(R.id.course_name_details)
        val str = courseCode + "\n\n" + courseName
        course_name_details.text = str
    }
}