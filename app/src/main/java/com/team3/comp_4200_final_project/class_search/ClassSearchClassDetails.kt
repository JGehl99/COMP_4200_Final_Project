package com.team3.comp_4200_final_project.class_search

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
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
import com.team3.comp_4200_final_project.db.Course
import java.util.*
import kotlin.collections.ArrayList

class ClassSearchClassDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_search_class_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Start up DB and Dao
        val db = AppDatabase.getInstance(this)
        val courseDao = db.courseDao()

        //Get values from intent
        val courseName = intent.getStringExtra("courseName") ?: ""
        val courseCode = intent.getStringExtra("courseCode") ?: ""
        val profName = intent.getStringExtra("profName") ?: ""
        val courseTimes = intent.getStringExtra("timeRange") ?: ""
        val courseDays = intent.getStringExtra("courseDays") ?: ""
        val courseLocation = intent.getStringExtra("courseLocation") ?: ""

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

        // onClick to add Course to db
        addButton.setOnClickListener {
            val course = Course(
                0,
                courseCode,
                courseName,
                courseTimes,
                courseDays,
                courseLocation,
                profName
            )
            courseDao.insert(course)

            val notifTimes = getNotificationTimes(course)

            val TAG = "NotifReceiverClass"
            for (time in notifTimes)
                Log.d(TAG, "onReceive: " + time.time)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(applicationContext, NotificationReceiver::class.java)
            intent.putExtra("courseName", course.courseName)
            intent.putExtra("courseTime", course.courseTimeRange)

            for (i in notifTimes.indices) {
                val pendingIntent = PendingIntent.getBroadcast(
                    applicationContext, course.id * 10 + i,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT
                )

                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    notifTimes[i].timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }
            finish()    // Close activity
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

    fun dayStrToInt(string: String): Int{
        when (string) {
            "Sunday" -> return Calendar.SUNDAY
            "Monday" -> return Calendar.MONDAY
            "Tuesday" -> return Calendar.TUESDAY
            "Wednesday" -> return Calendar.WEDNESDAY
            "Thursday" -> return Calendar.THURSDAY
            "Friday" -> return Calendar.FRIDAY
            "Saturday" -> return Calendar.SATURDAY
        }
        return -1 // Will only be returned when there is an issue
    }

    fun getNotificationTimes(course: Course): ArrayList<Calendar>{
        val notifTimes = ArrayList<Calendar>()
        val classDays = course.courseDays
            .split(",")
            .toMutableList()

        for (i in classDays.indices) {
            classDays[i] = classDays[i].trim()
        }

        for (i in classDays.indices) {
            val notifTime = Calendar.getInstance()

            // Correcting the time
            val startTimeString = course.courseTimeRange
                .split("-")[0]
                .trim()
                .split(":")

            notifTime.set(Calendar.HOUR_OF_DAY, startTimeString[0].toInt())
            notifTime.set(Calendar.MINUTE, startTimeString[1].toInt() - 15)
            notifTime.set(Calendar.SECOND, 0)

            // Correcting the Day
            val day = dayStrToInt(classDays[i])
            val dayDiff = day - notifTime.get(Calendar.DAY_OF_WEEK)
            val dayOfMonth = Calendar.DAY_OF_MONTH
            notifTime.set(Calendar.DAY_OF_MONTH, notifTime.get(dayOfMonth) + dayDiff)
            val dateDiff = Calendar.getInstance().timeInMillis - notifTime.timeInMillis
            if (dateDiff > 0) {
                notifTime.set(dayOfMonth, notifTime.get(dayOfMonth) + 7)
            }

            notifTimes.add(notifTime)
        }

        return notifTimes
    }
}