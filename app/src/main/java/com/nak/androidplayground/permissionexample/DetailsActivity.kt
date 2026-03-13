package com.nak.androidplayground.permissionexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nak.androidplayground.R

class DetailsActivity : AppCompatActivity() {

    private val TAG = "DetailsActivity"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        Log.d(TAG, "onCreate: ")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataFromMainActivity = intent.getStringExtra("data")

        Log.d(TAG, "data sent from main activity: $dataFromMainActivity")

        val button = findViewById<Button>(R.id.launch_gallery_button)
        button.setOnClickListener {
            val intent = Intent(this@DetailsActivity, GalleryActivity::class.java)
            startActivity(intent)
        }

        val setOKResultButton = findViewById<Button>(R.id.set_result_button)
        setOKResultButton.setOnClickListener {
            val data = Intent()
            data.putExtra("data", "Result from details activity")
            setResult(RESULT_OK, data)
            finish()
        }

        val setNoResultButton = findViewById<Button>(R.id.set_no_result_button)
        setNoResultButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
}