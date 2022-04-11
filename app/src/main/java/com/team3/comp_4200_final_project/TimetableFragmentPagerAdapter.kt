package com.team3.comp_4200_final_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TimetableFragmentPagerAdapter(
    fm: FragmentManager,
    private val dayArrayList: ArrayList<String>,
    private val week: SchoolWeek,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = dayArrayList.size

    override fun createFragment(position: Int): Fragment =
        DayFragment.newInstance(week.weekHashMap[dayArrayList[position]]?:ArrayList())
}