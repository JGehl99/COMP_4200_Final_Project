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
    private var arr: ArrayList<ClassData> = ArrayList()  // Creating ArrayList to hold Cards
    private lateinit var reAdapter: RecyclerAdapter // Initializing reAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_class_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reView = view.findViewById(R.id.recycler_view)

        reAdapter = RecyclerAdapter(arr)
        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter
    }
}
