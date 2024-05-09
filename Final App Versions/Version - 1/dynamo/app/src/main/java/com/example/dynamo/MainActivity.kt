package com.example.dynamo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import android.widget.VideoView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var crimeData: CrimeData
    private lateinit var crimeSpinner: Spinner
    private lateinit var videoView: VideoView
    private lateinit var approveButton: Button
    private lateinit var disapproveDropdown: Spinner
    private lateinit var textView: TextView
    private lateinit var createDataButton: Button
    private lateinit var crimeInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crimeData = CrimeData(resources)
        crimeSpinner = findViewById(R.id.crimeSpinner)
        videoView = findViewById(R.id.videoView)
        approveButton = findViewById(R.id.approveButton)
        disapproveDropdown = findViewById(R.id.disapproveDropdown)
        textView = findViewById(R.id.textView)
        createDataButton = findViewById(R.id.createDataButton)
        crimeInfo = findViewById(R.id.crimeInfoTextView)

        // Set up disapprove dropdown spinner
        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Shooting", "Burglary", "Normal")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        disapproveDropdown.adapter = adapter

        approveButton.setOnClickListener {
            val selectedVideoId = crimeSpinner.selectedItem as Int
            storeVideoData(selectedVideoId, "Approve")
        }

        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedAction = disapproveItems[position]
                if (selectedAction != "Disapprove") {
                    val selectedVideoId = crimeSpinner.selectedItem as Int
                    storeVideoData(selectedVideoId, "Disapprove", selectedAction)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
            }
        }

        createDataButton.setOnClickListener {
            createCSV()
        }

        // Populate crime spinner
        val videoIds = crimeData.dataCrimeList.map { it.video_id }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, videoIds)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        crimeSpinner.adapter = spinnerAdapter

        // Set listener for spinner item selection
        crimeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedVideoId = videoIds[position]
                val selectedCrime = crimeData.dataCrimeList.find { it.video_id == selectedVideoId }
                selectedCrime?.let { displayCrimeData(it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }
    }

    private fun storeVideoData(videoId: Int, action: String, subclass: String = "") {
        try {
            val csvFile = File(filesDir, "crime_data.csv")
            val fileWriter = FileWriter(csvFile, true)

            if (!csvFile.exists()) {
                csvFile.createNewFile()
                fileWriter.append("video_id, nature_crime, risk_level, latitude, longitude, video_link, approve/disapprove, subclass if disapprove\n")
            }

            val crime = crimeData.dataCrimeList.find { it.video_id == videoId }
            crime?.let {
                it.approveStatus = action
                it.subclass = subclass
            }

            // Write data to CSV
            val entry = buildString {
                append("${crime?.video_id ?: ""}, ")
                append("${crime?.natureCrime ?: ""}, ")
                append("${crime?.Risklevel ?: ""}, ")
                append("${crime?.latitude ?: ""}, ")
                append("${crime?.longitude ?: ""}, ")
                append("${crime?.video_url ?: ""}, ")
                append("${crime?.approveStatus ?: ""}, ")
                append("${crime?.subclass ?: ""}\n")
            }

            fileWriter.append(entry)
            fileWriter.flush()
            fileWriter.close()

            Toast.makeText(this, "CSV file updated successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to update CSV file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createCSV() {
        try {
            val csvFile = File(filesDir, "crime_data.csv")
            val fileWriter = FileWriter(csvFile)

            // Write header to the CSV file
            fileWriter.append("video_id, nature_crime, risk_level, latitude, longitude, video_link, approve/disapprove, subclass if disapprove\n")

            // Iterate through each crime entry and write it to the CSV file
            for (crime in crimeData.dataCrimeList) {
                val entry = "${crime.video_id}, ${crime.natureCrime}, ${crime.Risklevel}, ${crime.latitude}, ${crime.longitude}, ${crime.video_url}, ${crime.approveStatus}, ${crime.subclass}\n"
                fileWriter.append(entry)
            }

            // Flush and close the file writer
            fileWriter.flush()
            fileWriter.close()

            Toast.makeText(this, "CSV file created successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to create CSV file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayCrimeData(crime: CrimeData.CrimeValues) {
        // Display the crime video in the videoView
        videoView.setVideoPath(crime.video_url)
        videoView.start()

        // Display the fetched data in TextView
        crimeInfo.text = "Video ID: ${crime.video_id}\nNature of Crime: ${crime.natureCrime}\nRisk Level: ${crime.Risklevel}\nLatitude: ${crime.latitude}\nLongitude: ${crime.longitude}"
    }
}
