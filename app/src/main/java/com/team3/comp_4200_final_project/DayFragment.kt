package com.team3.comp_4200_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team3.comp_4200_final_project.db.Course

// Value that is passed in through bundle
private lateinit var day: String
private lateinit var classes: ArrayList<Course>

// Fragment for days of the week
class DayFragment : Fragment() {

    // Overriding onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day, container, false)
    }

    // Overriding onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get passed in ArrayList of ClassData
        arguments?.let {
            classes = it.getSerializable("classes") as ArrayList<Course>
        }

        val reView: RecyclerView = view.findViewById(R.id.recycler_view)

        // Pass classes to RecyclerAdapter to create list of classes using this info
        val reAdapter = TimetableRecyclerAdapter(context, classes)

        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter
    }

    // Factory method to create new instance of DayFragment, passing in bundle info
    companion object {
        fun newInstance(classes: ArrayList<Course>) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("classes", classes)
                }
            }
    }
}