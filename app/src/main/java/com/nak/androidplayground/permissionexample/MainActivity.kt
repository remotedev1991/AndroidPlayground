package com.nak.androidplayground.permissionexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nak.androidplayground.R
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var uri: Uri
    private lateinit var file: File
    private val TAG = "MainActivity"

    val launcher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uri: List<Uri>? ->
            val imageView = findViewById<ImageView>(R.id.image_view)
            imageView.setImageURI(uri?.get(0))
        }

    val launcherForGettingResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        //
    }

    val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted ->
            if(isGranted) {
                uri = FileProvider.getUriForFile(
                    this,
                    "${packageName}.provider",
                    file
                ) //path of the clicked image
                cameraLauncher.launch(uri)
            } else {
                Log.d(TAG, "permission has been denied: ")
            }
        }

    val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        saved ->
        if(saved) {
            val imageView = findViewById<ImageView>(R.id.image_view)
            imageView.setImageURI(uri)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "clicccked.jpg")
        Log.d(TAG, "onCreate: ")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.launch_details_screen)
        val launchCamera = findViewById<Button>(R.id.launch_camera)

        launchCamera.setOnClickListener {
            permissionLauncher.launch(Manifest.permission.CAMERA)

            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            launcherForGettingResult.launch(intent)

        }



        button.setOnClickListener {
            val intent = Intent(ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "I am from different application!!")
            startActivity(intent)
        }

        //MainActivity -> DetailsActivity -> GalleryActivity - standard

        //DetailsActivity -> GalleryActivity -> MainActivity (SingleTop)

        //SplashActivity -> MainActivity (SingleTask)

        //ListActivity



    }
}