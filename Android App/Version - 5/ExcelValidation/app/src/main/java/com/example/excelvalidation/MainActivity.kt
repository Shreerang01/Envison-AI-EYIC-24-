package com.example.excelvalidation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class VideoDashboardActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var approveButton: Button
    private lateinit var disapproveButton: Button

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.playerView)
        approveButton = findViewById(R.id.approveButton)
        disapproveButton = findViewById(R.id.disapproveButton)

        val videoUrl = "https://test1770.s3.amazonaws.com/videos/101_Test.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=0pfkmpQpRh3nqVk8i9NyQ6kXuKk%3D&Expires=1707240998"

        // Initialize ExoPlayer
        player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        // Handle button clicks
        approveButton.setOnClickListener { storeVideoId("approve.xlsx", videoUrl) }
        disapproveButton.setOnClickListener { storeVideoId("disapprove.xlsx", videoUrl) }
    }

    private fun storeVideoId(filename: String, videoUrl: String) {
        try {
            val file = File(filesDir, filename)
            val workbook = if (file.exists()) {
                WorkbookFactory.create(file.inputStream())
            } else {
                XSSFWorkbook()
            }

            val sheet: Sheet = workbook.getSheetAt(0) ?: workbook.createSheet("Video Records")
            val row: Row = sheet.createRow(sheet.physicalNumberOfRows)

            val timestampCell: Cell = row.createCell(0)
            timestampCell.setCellValue(dateFormat.format(Date()))

            val videoUrlCell: Cell = row.createCell(1)
            videoUrlCell.setCellValue(videoUrl)

            val approveCell: Cell = row.createCell(2)
            if (filename == "approve.xlsx") {
                approveCell.setCellValue("Approved")
            } else {
                approveCell.setCellValue("Disapproved")
            }

            val fileOut = FileOutputStream(file)
            workbook.write(fileOut)
            fileOut.close()
            workbook.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
