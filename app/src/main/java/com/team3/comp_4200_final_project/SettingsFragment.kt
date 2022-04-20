package com.team3.comp_4200_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val spinner = view.findViewById<Spinner>(R.id.spinner_notifTime)
        val sharedPreferences = this.activity?.getSharedPreferences("LocalPrefs", AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences?.edit()

        if (sharedPreferences != null) {
            spinner.setSelection(sharedPreferences.getInt("selected_index", 0))
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if (sharedPreferencesEditor != null) {
                    sharedPreferencesEditor.putInt("selected_index", position)
                    sharedPreferencesEditor.putInt("selectied_time", spinStrToInt(spinner.selectedItem.toString()))

                    sharedPreferencesEditor.apply()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        return view
    }

    fun spinStrToInt(string: String): Int {
        var ret = 0
        when (string) {
            "15 Minutes Before" -> ret = 15
            "30 Minutes Before" -> ret = 30
            "1 Hour Before" -> ret = 60
        }
        return ret
    }
}