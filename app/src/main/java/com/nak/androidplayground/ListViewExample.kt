package com.nak.androidplayground

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewExample : AppCompatActivity() {

    private var listView: ListView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val items = MutableList(4) { index ->
            "Item $index"
        }
        listView = findViewById(R.id.list_view) //find the object
        val courseAdapter = CourseAdapter(this, items)
        listView?.adapter = courseAdapter



        //Dynamically add new item

        var newItemText: String = ""
        val editText = findViewById<EditText>(R.id.new_text_view)
        val addNewItemButton = findViewById<Button>(R.id.add_new_text)

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                newItemText = p0.toString()
            }

            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

        })

        addNewItemButton.setOnClickListener {
              items.add(newItemText) //adding item in the main list
              editText.setText("") //removing the text from text field
              courseAdapter.submitList(items) //refreshing the adapter
              newItemText = ""
        }

    }
}