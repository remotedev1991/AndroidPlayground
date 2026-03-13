package com.nak.androidplayground.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nak.androidplayground.R

class ProfilesAdapter(val profilesList: MutableList<Profile>) :
    RecyclerView.Adapter<ProfilesAdapter.ItemViewHolder>() {
    private val TAG = "ProfilesAdapter"
    private var createViewsCount: Int = 0
    private var bindViewsCount: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        Log.d(TAG, "onCreateViewHolder: ${createViewsCount++}")
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_profile_item,
                parent,
                false
            )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ItemViewHolder,
        position: Int
    ) {
        Log.d(TAG, "onBindViewHolder: ${bindViewsCount++}")
        val profile = profilesList.get(position) //data
        //binding the data with views
        viewHolder.profileTitle.text = profile.name
        viewHolder.profileImage.setImageResource(profile.profileImage)
    }

    override fun getItemCount(): Int {
        return profilesList.size //total items
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileTitle = itemView.findViewById<TextView>(R.id.profile_name)
        val profileImage = itemView.findViewById<ImageView>(R.id.profile_image)
    }
}