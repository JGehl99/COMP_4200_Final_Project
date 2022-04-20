package com.team3.comp_4200_final_project

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.team3.comp_4200_final_project.class_search.ClassSearchFragment
import com.team3.comp_4200_final_project.timetable.TimetableFragment

private lateinit var drawerLayout: DrawerLayout
private lateinit var actionBarToggle: ActionBarDrawerToggle
private lateinit var navView: NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Display timetable fragment
        supportFragmentManager.commit {
            replace(R.id.fragment, TimetableFragment(0))
        }

        // Get drawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Drawer Toggler
        actionBarToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            0,
            0
        )

        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()

        // Get Nav view
        navView = findViewById(R.id.navView)

        // Listener to change fragment based on which option was chosen in the nav drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.timetable -> {
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragment,
                            TimetableFragment(0)
                        )
                    }
                }
                R.id.class_search -> {
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragment,
                            ClassSearchFragment()
                        )
                    }
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

    // onResume to reload TimetableFragment when course is deleted, also will keep the screen on the
    // same tab
    override fun onResume() {
        super.onResume()
        val frag = supportFragmentManager.findFragmentById(R.id.fragment)
        if (frag is TimetableFragment) {
            val tab = frag.getTab()
            supportFragmentManager.commit{
                replace(
                    R.id.fragment,
                    TimetableFragment(tab)
                )
            }
        }
    }
}
