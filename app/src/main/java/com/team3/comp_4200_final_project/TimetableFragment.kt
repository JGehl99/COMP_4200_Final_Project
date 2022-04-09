package com.team3.comp_4200_final_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TimetableFragment : Fragment() {

    // List of tab names
    private val tabs = arrayListOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    // lateinit View objects
    private lateinit var classSearchFAB: FloatingActionButton
    private lateinit var fragmentPagerAdapter: TimetableFragmentPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    // Overriding onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get view
        val view =  inflater.inflate(R.layout.fragment_timetable,container,false)

        // TimetableFragmentPagerAdapter extends FragmentStateAdapter()
        fragmentPagerAdapter = TimetableFragmentPagerAdapter(childFragmentManager, tabs,  lifecycle)

        // Get viewPager View and set the adapter
        viewPager = view.findViewById(R.id.timetable_view_pager)
        viewPager.adapter = fragmentPagerAdapter

        // Get tabLayout View and bind it to viewPager with TabLayoutMediator.
        // The lambda will set the tab text based on the value at that position in the array
        tabLayout = view.findViewById(R.id.timetable_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()

        // Get classSearchFAB View and set OnClickListener to launch SearchActivity
        classSearchFAB = view.findViewById(R.id.class_search_FAB)
        classSearchFAB.setOnClickListener {
            val i = Intent(view.context, SearchActivity::class.java)
            startActivity(i)
        }

        // Return view
        return view
    }
}