package com.example.realmsetup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import java.io.File
import java.io.FileWriter

class VideoDashboardActivity : AppCompatActivity() {

private lateinit var videoView: VideoView
private lateinit var approveButton: Button
private lateinit var disapproveDropdown: Spinner
private lateinit var textView: TextView
private lateinit var createDataButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        approveButton = findViewById(R.id.approveButton)
        disapproveDropdown = findViewById(R.id.disapproveDropdown)
        textView = findViewById(R.id.textView)
        createDataButton = findViewById(R.id.createDataButton)

        // Set up DynamoDB client
        val credentialsProvider = CognitoCachingCredentialsProvider(
        applicationContext,
        "ap-south-1_NbLpJW9ra",
        Regions.AP_SOUTH_1

        val dynamoDBClient = AmazonDynamoDBClient(credentialsProvider)
        val dynamoDB = DynamoDB(dynamoDBClient)
        val table = Table.loadTable(dynamoDB, "test")

        // Set up disapprove dropdown spinner
        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        disapproveDropdown.adapter = adapter

        approveButton.setOnClickListener {
        val videoUrl = "YOUR_VIDEO_URL"
        storeVideoData(videoUrl, "Approve")
        }

        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedAction = disapproveItems[position]
        if (selectedAction != "Disapprove") {
        val videoUrl = "YOUR_VIDEO_URL"
        storeVideoData(videoUrl, "Disapprove", selectedAction)
        }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        // Handle the case when nothing is selected
        }
        }

        createDataButton.setOnClickListener {
        pullDataFromDynamoDB(table)
        }
        }

private fun storeVideoData(videoUrl: String, action: String, subclass: String = "") {
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

private fun pullDataFromDynamoDB(table: Table) {
        val scanSpec = ScanSpec()
        val items = table.scan(scanSpec)

        val dataStringBuilder = StringBuilder()
        dataStringBuilder.append("videoid, nature of crime, risk level, latitude, longitude, video_url, approve/disapprove, subclass if disapprove\n")

        for (item in items) {
        // Extract data from DynamoDB item
        val videoId = item.getString("videoid")
        val natureOfCrime = item.getString("natureofcrime")
        val riskLevel = item.getString("risklevel")
        val latitude = item.getString("latitude")
        val longitude = item.getString("longitude")
        val videoUrl = item.getString("videourl")

        // Append data to StringBuilder
        dataStringBuilder.append("$videoId, $natureOfCrime, $riskLevel, $latitude, $longitude, $videoUrl\n")
        }

        // Display the fetched data in TextView
        textView.text = dataStringBuilder.toString()
        }
        }
