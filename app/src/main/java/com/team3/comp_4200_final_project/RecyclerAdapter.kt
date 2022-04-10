package com.team3.comp_4200_final_project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (c: Context?, private val arr: ArrayList<ClassData>): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>(){

    private lateinit var cardView: CardView     // Initializing CardView
    private var context: Context? = c            // Context passed in

    // ItemViewHolder class, get Views
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseCodeTextView: TextView = itemView.findViewById(R.id.course_code)
        val courseNameTextView: TextView = itemView.findViewById(R.id.course_name)
        val courseTimeRangeTextView: TextView = itemView.findViewById(R.id.course_time_range)
        val courseDays: TextView = itemView.findViewById(R.id.course_days)
        val cardView: CardView = itemView.findViewById(R.id.course_card)
    }

    // onCreateViewHolder, inflate and return ItemVIewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.recycler_view_item_layout, parent, false)
        return ItemViewHolder(contactView)
    }

    // onBindViewHolder, gets information from array and assigns it to corresponding holder values
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.courseCodeTextView.text = arr[position].courseCode
        holder.courseNameTextView.text = arr[position].courseName
        holder.courseTimeRangeTextView.text = arr[position].courseTimeRange
        holder.courseDays.text = arr[position].courseDays
        holder.cardView.setOnClickListener {
            // TODO: Add on click listener to either: bring up course page, or add to timetable
             val i = Intent(this.context, ClassDetails::class.java).apply {
                 putExtra("courseName", arr[position].courseName)
                 putExtra("courseCode", arr[position].courseCode)
             }
            context?.startActivity(i)
        }
    }

    override fun getItemCount(): Int = arr.size
}
