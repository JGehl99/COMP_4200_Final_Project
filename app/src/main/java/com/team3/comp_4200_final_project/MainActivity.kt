package com.team3.comp_4200_final_project

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import java.sql.Time

private lateinit var drawerLayout: DrawerLayout
private lateinit var actionBarToggle: ActionBarDrawerToggle
private lateinit var navView: NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: Retrieve the information for the user's timetable in MainActivity, and pass it
        // along to TimetableFragment, which will then pass it along to DayFragment, using this
        // ArrayList as a placeholder until we decide the structure of the data structure

        // Data required to be passed (Strings):
        // Course Code
        // Course Name
        // Course Start Time
        // Course End Time
        // Course Days (ex: MWF, must parse in app)
        // Course Location (building w/ #, Livestream, or Online)
        // Course Professor
        val week = SchoolWeek()
        week.addClass(ClassData(
            "COMP-3340",
            "World Wide Web",
            "7:00pm",
            "9:50pm",
            "F",
            "Online",
            "Ziad Kobti"
        ))
        week.addClass(ClassData(
            "COMP-3340",
            "World Wide Web",
            "7:00pm",
            "9:50pm",
            "F",
            "Online",
            "Ziad Kobti"
        ))
        week.addClass(ClassData(
            "COMP-3340",
            "World Wide Web",
            "7:00pm",
            "9:50pm",
            "F",
            "Online",
            "Ziad Kobti"
        ))
        week.addClass(ClassData(
            "COMP-4540",
            "Theory of Computation",
            "1:00pm",
            "2:30pm",
            "MW",
            "Erie 2110",
            "Peter Tsin"
        ))
        week.addClass(ClassData(
            "COMP-4200",
            "Mobile App Dev",
            "11:30am",
            "12:50pm",
            "TTH",
            "Livestream (Hyflex)",
            "Shaon Shuvo"
        ))
        week.addClass(ClassData(
            "ANZO-1000",
            "Animals and Humans in Society",
            "11:30am",
            "12:50pm",
            "TF",
            "Livestream (Hyflex)",
            "Jane Smith"
        ))

        // Display main fragment
        supportFragmentManager.commit {
            replace(R.id.fragment, TimetableFragment.newInstance(week))
        }

        // Get drawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Drawer Toggler
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()

        // Get Nav view
        navView = findViewById(R.id.navView)

        // Listener to change fragment based on which option was chosen in the nav drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.timetable -> {
                    supportFragmentManager.commit { replace(R.id.fragment, TimetableFragment.newInstance(week)) }
                }
                R.id.class_search -> {
                    supportFragmentManager.commit { replace(R.id.fragment, ClassSearchFragment()) }
                }
                R.id.settings -> {
                    supportFragmentManager.commit { replace(R.id.fragment, SettingsFragment()) }
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
