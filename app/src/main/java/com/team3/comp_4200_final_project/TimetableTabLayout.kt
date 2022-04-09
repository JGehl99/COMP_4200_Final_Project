package com.team3.comp_4200_final_project

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

// Custom TabLayout to add separators between tabs, and to stretch tabs to fit TabBar if there aren't
// enough tabs to cover the whole bar
class TimetableTabLayout: TabLayout {
    // TabLayout constructors
    constructor(c: Context) : super(c)
    constructor(c: Context, a: AttributeSet): super(c, a)
    constructor(c:Context, a: AttributeSet, dsa: Int) : super(c, a, dsa)

    // This code is ran on initialization, add separator between tabs
    init {
        val root = this.getChildAt(0) as LinearLayout
        root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val drawable = GradientDrawable()
        drawable.setColor(ContextCompat.getColor(context, R.color.separator))
        drawable.setSize(5, 3)
        root.dividerPadding = 10
        root.dividerDrawable = drawable
    }

    // Override onMeasure to make tabs take up entire TabBar if there aren't
    // enough tabs to cover the whole bar
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val tabLayout = getChildAt(0) as ViewGroup
        val childCount = tabLayout.childCount

        if (childCount != 0) {
            val displayMetrics = context.resources.displayMetrics
            val tabMinWidth = displayMetrics.widthPixels / childCount
            for (i in 0 until childCount) {
                tabLayout.getChildAt(i).minimumWidth = tabMinWidth
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}