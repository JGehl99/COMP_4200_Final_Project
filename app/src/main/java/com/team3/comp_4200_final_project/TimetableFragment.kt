package com.team3.comp_4200_final_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TimetableFragment : Fragment() {

    private lateinit var classSearchFAB: FloatingActionButton;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_timetable,container,false)

        classSearchFAB = view.findViewById(R.id.class_search_FAB)

        classSearchFAB.setOnClickListener {
            val i = Intent(view.context, SearchActivity::class.java)
            startActivity(i)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}