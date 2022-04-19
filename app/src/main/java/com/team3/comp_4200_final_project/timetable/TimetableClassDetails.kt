package com.team3.comp_4200_final_project.timetable

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team3.comp_4200_final_project.NotificationReceiver
import com.team3.comp_4200_final_project.R
import com.team3.comp_4200_final_project.db.AppDatabase

class TimetableClassDetails : AppCompatActivity() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_class_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val db = AppDatabase.getInstance(this)
        val courseDao = db.courseDao()

        //Get values from intent
        val courseName = intent.getStringExtra("courseName") ?: ""
        val courseCode = intent.getStringExtra("courseCode") ?: ""
        val profName = intent.getStringExtra("profName") ?: ""
        val courseTimes = intent.getStringExtra("timeRange") ?: ""
        val courseDays = intent.getStringExtra("courseDays") ?: ""
        val courseLocation = intent.getStringExtra("courseLocation") ?: ""
        val courseID = intent.getIntExtra("id", -1)

        //Get and assign the corresponding textViews
        val courseNameDetailsTextView: TextView = findViewById(R.id.course_name_details)
        val courseProfTextView: TextView = findViewById(R.id.prof)
        val courseTimesTextView: TextView = findViewById(R.id.times)
        val courseDaysTextView: TextView = findViewById(R.id.days)
        val courseLocationTextView: TextView = findViewById(R.id.location)
        val addButton: Button = findViewById(R.id.add_to_cal)

        //Set all the information about the course
        val nameCode = courseCode + "\n\n" + courseName
        courseNameDetailsTextView.text = nameCode

        val professorName = "Professor: $profName"
        courseProfTextView.text = professorName.replace("[","").replace("]","")

        val classDays = "Class held on: $courseDays"
        courseTimesTextView.text = classDays

        val classTimes = "Time of lecture: $courseTimes"
        courseDaysTextView.text = classTimes

        val classLocation = "Location: $courseLocation"
        courseLocationTextView.text = classLocation

        // onClick to remove course from timetable
        addButton.setOnClickListener {
            // remove alarmManagers for notifications
            val numOfClassDays = classDays.split(",")
            val TAG = "NotifReceiverClass"

            for (i in numOfClassDays.indices) {
                Log.d(TAG, "setOnClickListener: " + (courseID * 10 + i))
                val pendingIntent = PendingIntent.getBroadcast(
                    this,
                    courseID * 10 + i,
                    Intent(this, NotificationReceiver::class.java),
                    PendingIntent.FLAG_NO_CREATE
                )
                pendingIntent?.cancel()
            }

            // remove from DB
            courseDao.delete(courseID)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}