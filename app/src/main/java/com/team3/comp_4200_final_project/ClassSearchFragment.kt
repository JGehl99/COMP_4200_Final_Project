package com.team3.comp_4200_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClassSearchFragment : Fragment() {

    private lateinit var reView: RecyclerView       // Initializing reView
    private var arr: ArrayList<RecyclerViewCard> = ArrayList()  // Creating ArrayList to hold Cards
    private lateinit var reAdapter: RecyclerAdapter // Initializing reAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_class_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arr.add(RecyclerViewCard("COMP-4540", "Theory of Computation", "1:00pm-2:20pm", "Mon, Wed"))
        arr.add(RecyclerViewCard("COMP-3670", "Computer Networks", "7:00pm-9:50pm", "Wed"))
        arr.add(RecyclerViewCard("COMP-3340", "World Wide Web", "8:30am-9:50am", "Tues, Thurs"))

        reView = view.findViewById(R.id.recycler_view)

        reAdapter = RecyclerAdapter(view.context, arr)
        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter
    }
}
