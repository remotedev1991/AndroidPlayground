package com.nak.androidplayground

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpinnerExample : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spinner_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.spiner_view)

        val languages = listOf<String>(
            "Kotlin",
            "Java",
            "Python",
            "JavaScript",
            "Go",
            "Swift",
            "Rust"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            languages
        )

        var selectedLanguage = languages.first()

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                itemView: View?,
                position: Int,
                id: Long
            ) {
                val item = parentView?.getItemAtPosition(position)

                selectedLanguage = languages[position]

                Log.d("TAG", "onItemSelected: $selectedLanguage")

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

}