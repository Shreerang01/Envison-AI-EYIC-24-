
package com.example.validation2

// MainActivity.kt
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var approveButton: Button
    private lateinit var disapproveButton: Button

    private val videoUrl = "https://test1770.s3.amazonaws.com/videos/795de05f-218a-4d0b-857c-d722e2b54ac1_Test.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIATJP6II4GP2T23LQN%2F20240129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240129T125858Z&X-Amz-Expires=21600&X-Amz-SignedHeaders=host&X-Amz-Signature=9323915167f8403d2c7f4ae08a1fa04eea45ca603da52b5dfc52c904261b5492"
    private val approveFile = "approve.txt"
    private val disapproveFile = "disapprove.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.playerView)
        approveButton = findViewById(R.id.approveButton)
        disapproveButton = findViewById(R.id.disapproveButton)

        initializePlayer()

        approveButton.setOnClickListener {
            saveToTextFile(videoUrl, approveFile)
        }

        disapproveButton.setOnClickListener {
            saveToTextFile(videoUrl, disapproveFile)
        }
    }

    private fun initializePlayer() {
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        playerView.player = exoPlayer
        exoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
        exoPlayer.prepare()
        exoPlayer.play()
    }

    private fun saveToTextFile(videoId: String, fileName: String) {
        val file = File(filesDir, fileName)
        file.appendText("$videoId\n")
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
