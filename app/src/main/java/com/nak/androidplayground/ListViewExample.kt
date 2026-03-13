package com.nak.androidplayground

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        val courseAdapter = CourseAdapter(this, items) { position ->
            //click the button will come here
            val builder = AlertDialog.Builder(this@ListViewExample)
            builder.setTitle("Are you sure ? ")
            builder.setMessage("The item will be removed from the list after this action.")
            builder.setPositiveButton("Yes") { dialog, which ->
                items.removeAt(position)
                val adapter = listView?.adapter as CourseAdapter
                adapter.notifyDataSetChanged()
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }
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