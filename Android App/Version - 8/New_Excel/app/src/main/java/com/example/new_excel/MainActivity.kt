package com.example.new_excel

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
import android.widget.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

class VideoDashboardActivity : AppCompatActivity() {

    private lateinit var videoUrl: String
    private lateinit var actionSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample video URL (Replace with your S3 URL)
        videoUrl = "https://test1770.s3.amazonaws.com/videos/111_Test.mp4?AWSAccessKeyId=AKIATJP6II4GHZ3K3GI3&Signature=33gSbeLS88DAH1DFmGNwWkeCyeE%3D&Expires=1707069453"

        // Initialize Spinner
        actionSpinner = findViewById(R.id.actionSpinner)
        val actions = resources.getStringArray(R.array.action_items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, actions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        actionSpinner.adapter = adapter

        // Spinner Item Selection Listener
        actionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedAction = actions[position]
                if (selectedAction != "Disapprove") {
                    saveToExcel(selectedAction, videoUrl)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
            }
        }
    }

    private fun saveToExcel(action: String, videoUrl: String) {
        val fileName = "video_urls.xlsx"
        val file = File(filesDir, fileName)

        val workbook = if (file.exists()) {
            XSSFWorkbook(file.inputStream())
        } else {
            XSSFWorkbook()
        }

        val sheet: Sheet = workbook.getSheet("Videos") ?: workbook.createSheet("Videos")

        val row: Row = sheet.getRow(0) ?: sheet.createRow(0)
        val columnIndex = when (action) {
            "Approve" -> 0
            "Assault" -> 1
            "Arson" -> 2
            "Abuse" -> 3
            "Burglary" -> 4
            "Robbery" -> 5
            else -> -1
        }

        val cell: Cell = row.createCell(columnIndex)
        cell.setCellValue(videoUrl)

        // Save Excel File
        FileOutputStream(file).use { fos ->
            workbook.write(fos)
        }
    }
}