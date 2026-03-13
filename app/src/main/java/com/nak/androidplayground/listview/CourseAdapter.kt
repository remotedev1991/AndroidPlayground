package com.nak.androidplayground.listview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.nak.androidplayground.R

class CourseAdapter(
    context: Context,
    private var courses: MutableList<String>,
    val onDeleteButtonClicked: (Int) -> Unit
) :

    ArrayAdapter<String>(context, 0, courses) {

    var countView: Int = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View
        var viewHolder: ViewHolder

        if (convertView == null) {
            Log.d("TAG", "getView: convertView creating new view ${countView++}")
            view = LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.textView.text = courses[position]
        viewHolder.button.setOnClickListener {
            onDeleteButtonClicked(position)
        }
        return view
    }

    fun submitList(list: MutableList<String>) {
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) {
        val textView = view.findViewById<TextView>(R.id.text_view)
        val button = view.findViewById<Button>(R.id.enroll_button)
    }
}