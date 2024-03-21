package com.example.structure

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
        val videoUrl = "https://test1770.s3.amazonaws.com/videos/129_Untitled%20design.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=QLN6koVJNj6IdeJ9bMUxWlWLEdc%3D&Expires=1707667026"

        // Set the video URL and start playing
        videoView.setVideoPath(videoUrl)
        videoView.start()

        // Initialize the disapprove dropdown spinner
        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        disapproveDropdown.adapter = adapter

        // Set the click listener for the approve button
        approveButton.setOnClickListener {
            storeVideoId(videoUrl, "Approve")
        }

        // Set the item selected listener for the disapprove dropdown spinner
        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedAction = disapproveItems[position]
                if (selectedAction != "Disapprove") {
                    storeVideoId(videoUrl, "Disapprove", selectedAction)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
            }
        }
    }

    private fun storeVideoId(videoUrl: String, action: String, subclass: String = "") {
        try {
            val csvFile = File(filesDir, "video_data.csv")
            val fileWriter = FileWriter(csvFile, true)

            if (!csvFile.exists()) {
                csvFile.createNewFile()
                fileWriter.append("Video URL, Action, Subclass\n")
            }

            fileWriter.append("$videoUrl, $action, $subclass\n")
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
