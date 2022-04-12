package com.team3.comp_4200_final_project

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.team3.comp_4200_final_project.db.AppDatabase
import com.team3.comp_4200_final_project.db.Course
import com.team3.comp_4200_final_project.db.CourseDao

class TimetableFragmentPagerAdapter(
    fm: FragmentManager,
    private val dayArrayList: ArrayList<String>,
    lifecycle: Lifecycle,
    context: Context
) : FragmentStateAdapter(fm, lifecycle) {

    private val week = SchoolWeek()                     // Holds class info
    private val db = AppDatabase.getInstance(context)   // db
    private val courseDao = db.courseDao()              // Dao

    // Get all courses from db and populate SchoolWeek
    init {
        val courseList: List<Course> = courseDao.getAll()
        for (course in courseList) {
            week.addClass(course)
        }
    }

    override fun getItemCount(): Int = dayArrayList.size

    // Pass ArrayList of classes for specific day to corresponding DayFragment
    override fun createFragment(position: Int): Fragment {
        return DayFragment.newInstance(week.weekHashMap[dayArrayList[position]]?:ArrayList())
    }

}