package com.team3.comp_4200_final_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// Value that is passed in through bundle
private lateinit var day: String

// Fragment for days of the week
class DayFragment : Fragment() {

    // Overriding onCreateView
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get view
        val view = inflater.inflate(R.layout.fragment_day, container, false)

        // Get passed in string, set to empty string if null ( ?: is how you null-check in Kotlin)
        arguments?.let {
            day = it.getString("day")?: ""
        }

        // Get and set textView to contain name of the day
        val textView:TextView = view.findViewById(R.id.placeholder_text)
        val str = "Hello, $day!"
        textView.text = str

        // Return view
        return view
    }

    // Factory method to create new instance of DayFragment, passing in bundle info
    companion object {
        fun newInstance(day: String) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putString("day", day)
                }
            }
    }
}