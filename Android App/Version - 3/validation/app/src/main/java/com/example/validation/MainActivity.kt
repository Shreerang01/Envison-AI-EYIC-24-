package com.example.validation

import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter

class VideoDashboardActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var approveButton: Button
    private lateinit var disapproveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        approveButton = findViewById(R.id.approveButton)
        disapproveButton = findViewById(R.id.disapproveButton)

        // Sample video URL (Replace with your S3 URL)
        val videoUrl = "https://test1770.s3.amazonaws.com/videos/201_Test.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=Gd%2FMSQPyAdVy62OkwE7PnHbRQiY%3D&Expires=1707331516"
        videoView.setVideoPath(videoUrl)
        videoView.start()

        approveButton.setOnClickListener {
            storeVideoId("approve.txt", videoUrl)
        }

        disapproveButton.setOnClickListener {
            storeVideoId("disapprove.txt", videoUrl)
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
