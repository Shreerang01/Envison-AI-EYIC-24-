package com.example.six_validation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter

class VideoDashboardActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var approveButton: Button
    private lateinit var assaultButton: Button
    private lateinit var arsonButton: Button
    private lateinit var abuseButton: Button
    private lateinit var burglaryButton: Button
    private lateinit var robberyButton: Button
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.titleTextView)
        videoView = findViewById(R.id.videoView)
        approveButton = findViewById(R.id.approveButton)
        assaultButton = findViewById(R.id.assaultButton)
        arsonButton = findViewById(R.id.arsonButton)
        abuseButton = findViewById(R.id.abuseButton)
        burglaryButton = findViewById(R.id.burglaryButton)
        robberyButton = findViewById(R.id.robberyButton)

        // Sample video URL (Replace with your S3 URL)
        val videoUrl = "https://test1770.s3.amazonaws.com/videos/102_Test.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=qkFlJqExRlU1hgu%2F34vNPoqf79o%3D&Expires=1707244316"
        videoView.setVideoPath(videoUrl)
        videoView.start()

        approveButton.setOnClickListener {
            storeVideoId("approve.txt", videoUrl)
        }

        assaultButton.setOnClickListener {
            storeVideoId("assault.txt", videoUrl)
        }

        arsonButton.setOnClickListener {
            storeVideoId("arson.txt", videoUrl)
        }

        abuseButton.setOnClickListener {
            storeVideoId("abuse.txt", videoUrl)
        }

        burglaryButton.setOnClickListener {
            storeVideoId("burglary.txt", videoUrl)
        }

        robberyButton.setOnClickListener {
            storeVideoId("robbery.txt", videoUrl)
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
