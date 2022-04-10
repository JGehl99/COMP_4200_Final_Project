package com.team3.comp_4200_final_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Value that is passed in through bundle
private lateinit var day: String

// Fragment for days of the week
class DayFragment : Fragment() {

    // Overriding onCreateView
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_day, container, false)
    }

    // Overriding onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get passed in string, set to empty string if null ( ?: is how you null-check in Kotlin)
        arguments?.let {
            day = it.getString("day")?: ""
        }

        val arr: ArrayList<RecyclerViewCard> = ArrayList()  // Creating ArrayList to hold Cards
        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))
        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))
        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))
        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))
        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))


        val reView: RecyclerView = view.findViewById(R.id.recycler_view)
        val reAdapter = RecyclerAdapter(view.context, arr)

        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter

    }

    // Factory method to create new instance of DayFragment, passing in bundle info
    companion object {

        //TODO: In this function, we can retrieve the content for a specific day
        // and pass it into the fragment using the bundle, that way whenever a
        // fragment is created we can always pass it the schedule for that day.
        fun newInstance(day: String) =
            DayFragment().apply {
                arguments = Bundle().apply {
                    putString("day", day)
                }
            }
    }
}