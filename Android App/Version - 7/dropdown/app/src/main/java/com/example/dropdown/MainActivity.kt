package com.example.dropdown

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter

class VideoDashboardActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var approveButton: Button
    private lateinit var disapproveDropdown: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        approveButton = findViewById(R.id.approveButton)
        disapproveDropdown = findViewById(R.id.disapproveDropdown)

        // Sample video URL (Replace with your S3 URL)
        val videoUrl = "https://test1770.s3.amazonaws.com/videos/201_Test.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=Gd%2FMSQPyAdVy62OkwE7PnHbRQiY%3D&Expires=1707331516"
        videoView.setVideoPath(videoUrl)
        videoView.start()

        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        disapproveDropdown.adapter = adapter

        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedAction = disapproveItems[position]
                if (selectedAction != "Disapprove") {
                    storeVideoId("$selectedAction.txt", videoUrl)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
            }
        }

        approveButton.setOnClickListener {
            storeVideoId("approve.txt", videoUrl)
        }
    }

    private fun storeVideoId(filename: String, videoId: String) {
        try {
            val file = File(filesDir, filename)
            val fileWriter = FileWriter(file, true)
            fileWriter.append("$videoId\n")
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
