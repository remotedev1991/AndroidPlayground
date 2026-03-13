package com.nak.androidplayground.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nak.androidplayground.R

class RecyclerViewExample : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        //adapter -> What to show -> data and actions
        //layout manager -> How to show -> Horizontal, Vertical, Grid
        recyclerView.layoutManager = LinearLayoutManager(this)
        val profileAdapter = ProfilesAdapter(generateProfiles())
        recyclerView.adapter = profileAdapter
    }

    private fun generateProfiles(): MutableList<Profile> {
        return MutableList(10) { index ->
            Profile(
                name = "Person $index",
                profileImage = R.drawable.test
            )
        }
    }

    //viewholder pattern
    //built in animations
    //no support for multiple rows - solved by viewType in recycler view
    //layout manager
    //no built in decorations
    //complex UI problem - lagging
}