package com.team3.comp_4200_final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView

private lateinit var drawerLayout: DrawerLayout
private lateinit var actionBarToggle: ActionBarDrawerToggle
private lateinit var navView: NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Display main fragment
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragment, MainFragment())
        fram.commit()

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
                    supportFragmentManager.commit { replace(R.id.fragment, TimetableFragment()) }
//                    drawerLayout.closeDrawer(GravityCompat.START)
//                    true
                }
                R.id.class_search -> {
                    supportFragmentManager.commit { replace(R.id.fragment, ClassSearchFragment()) }
//                    drawerLayout.closeDrawer(GravityCompat.START)
//                    true
                }
                R.id.settings -> {
                    supportFragmentManager.commit { replace(R.id.fragment, SettingsFragment()) }
//                    drawerLayout.closeDrawer(GravityCompat.START)
//                    true
                }
//                else -> {
//                    false
//                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
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