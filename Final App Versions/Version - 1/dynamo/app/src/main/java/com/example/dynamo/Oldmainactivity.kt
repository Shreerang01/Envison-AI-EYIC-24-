package com.example.dynamo
//
//import android.os.Bundle
//import android.view.View
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import java.io.File
//import java.io.FileWriter
//
//class VideoDashboardActivity : AppCompatActivity() {
//
//    private lateinit var videoView: VideoView
//    private lateinit var approveButton: Button
//    private lateinit var disapproveDropdown: Spinner
//    private lateinit var textView: TextView
//    private lateinit var createDataButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        videoView = findViewById(R.id.videoView)
//        approveButton = findViewById(R.id.approveButton)
//        disapproveDropdown = findViewById(R.id.disapproveDropdown)
//        textView = findViewById(R.id.textView)
//        createDataButton = findViewById(R.id.createDataButton)
//
//        // Set up disapprove dropdown spinner
//        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        disapproveDropdown.adapter = adapter
//
//        approveButton.setOnClickListener {
//            val videoUrl = "YOUR_VIDEO_URL"
//            storeVideoData(videoUrl, "Approve")
//        }
//
//        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedAction = disapproveItems[position]
//                if (selectedAction != "Disapprove") {
//                    val videoUrl = "YOUR_VIDEO_URL"
//                    storeVideoData(videoUrl, "Disapprove", selectedAction)
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle the case when nothing is selected
//            }
//        }
//
//        createDataButton.setOnClickListener {
//            pullDataFromDynamoDB(table)
//        }
//    }
//
//    private fun storeVideoData(videoUrl: String, action: String, subclass: String = "") {
//        try {
//            val csvFile = File(filesDir, "video_data.csv")
//            val fileWriter = FileWriter(csvFile, true)
//
//            if (!csvFile.exists()) {
//                csvFile.createNewFile()
//                fileWriter.append("Video URL, Action, Subclass\n")
//            }
//
//            fileWriter.append("$videoUrl, $action, $subclass\n")
//            fileWriter.flush()
//            fileWriter.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun pullDataFromDynamoDB(table: Table) {
//        val scanSpec = ScanSpec()
//        val items = table.scan(scanSpec)
//
//        val dataStringBuilder = StringBuilder()
//        dataStringBuilder.append("videoid, nature of crime, risk level, latitude, longitude, video_url, approve/disapprove, subclass if disapprove\n")
//
//        for (item in items) {
//            // Extract data from DynamoDB item
//            val videoId = item.getString("videoid")
//            val natureOfCrime = item.getString("natureofcrime")
//            val riskLevel = item.getString("risklevel")
//            val latitude = item.getString("latitude")
//            val longitude = item.getString("longitude")
//            val videoUrl = item.getString("videourl")
//
//            // Append data to StringBuilder
//            dataStringBuilder.append("$videoId, $natureOfCrime, $riskLevel, $latitude, $longitude, $videoUrl\n")
//        }
//
//        // Display the fetched data in TextView
//        textView.text = dataStringBuilder.toString()
//    }
//}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//package com.example.dynamo
//
//import android.os.Bundle
//import android.view.View
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import java.io.File
//import java.io.FileWriter
//
//class VideoDashboardActivity : AppCompatActivity() {
//
//    private lateinit var videoView: VideoView
//    private lateinit var approveButton: Button
//    private lateinit var disapproveDropdown: Spinner
//    private lateinit var textView: TextView
//    private lateinit var createDataButton: Button
//    private val crimeData = CrimeData(resources)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        videoView = findViewById(R.id.videoView)
//        approveButton = findViewById(R.id.approveButton)
//        disapproveDropdown = findViewById(R.id.disapproveDropdown)
//        textView = findViewById(R.id.textView)
//        createDataButton = findViewById(R.id.createDataButton)
//
//        // Set up disapprove dropdown spinner
//        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        disapproveDropdown.adapter = adapter
//
//        approveButton.setOnClickListener {
//            val videoUrl = "YOUR_VIDEO_URL"
//            storeVideoData(videoUrl, "Approve")
//        }
//
//        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedAction = disapproveItems[position]
//                if (selectedAction != "Disapprove") {
//                    val videoUrl = "YOUR_VIDEO_URL"
//                    storeVideoData(videoUrl, "Disapprove", selectedAction)
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle the case when nothing is selected
//            }
//        }
//
//        createDataButton.setOnClickListener {
//            displayCrimeData()
//        }
//    }
//
//    private fun storeVideoData(videoUrl: String, action: String, subclass: String = "") {
//        try {
//            val csvFile = File(filesDir, "video_data.csv")
//            val fileWriter = FileWriter(csvFile, true)
//
//            if (!csvFile.exists()) {
//                csvFile.createNewFile()
//                fileWriter.append("Video URL, Action, Subclass\n")
//            }
//
//            fileWriter.append("$videoUrl, $action, $subclass\n")
//            fileWriter.flush()
//            fileWriter.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun displayCrimeData() {
//        val dataStringBuilder = StringBuilder()
//        dataStringBuilder.append("videoid, nature of crime, risk level, latitude, longitude, video_url\n")
//
//        for (crimeValue in crimeData.dataCrimeList) {
//            dataStringBuilder.append("${crimeValue.video_id}, ${crimeValue.natureCrime}, ${crimeValue.Risklevel}, ${crimeValue.latitude}, ${crimeValue.longitude}, ${crimeValue.video_url}\n")
//        }
//
//        // Display the fetched data in TextView
//        textView.text = dataStringBuilder.toString()
//    }
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//package com.example.dynamo
//
//import android.content.res.Resources
//
//class CrimeData(private val resources: Resources) {
//
//    data class CrimeValues(
//        val video_id: Int,
//        val natureCrime: String,
//        val Risklevel: Double,
//        val latitude: Double,
//        val longitude: Double,
//        val video_url: String
//    ) {
//        val levelRisk: String = when (Risklevel) {
//            in 20.0..40.0 -> "Low ($Risklevel)"
//            in 41.0..70.0 -> "Medium ($Risklevel)"
//            in 71.0..100.0 -> "High ($Risklevel)"
//            else -> "Invalid input"
//        }
//    }
//
//    val dataCrimeList = listOf(
//        CrimeValues(
//            1,
//            "Assault",
//            99.8837,
//            16.5424,
//            73.4567,
//            "https://envision0177.s3.amazonaws.com/videos/1_Fighting049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=e51ALWQCZLzbKpoUqGK0stwyL0M%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            2,
//            "Arson",
//            71.4778,
//            16.5424,
//            73.4567,
//            "https://envision0177.s3.amazonaws.com/videos/2_Arson011_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=pS4lz7iIE3V0Ikihzf4yzwe8nvg%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            3,
//            "Burglary",
//            37.8447,
//            16.5424,
//            73.4567,
//            "https://envision0177.s3.amazonaws.com/videos/3_Burglary021_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=EBU5M3YQ7GkgIqM%2FYUSzqPISA2w%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            4,
//            "Arson",
//            60.2807,
//            16.5424,
//            73.4567,
//            "https://envision0177.s3.amazonaws.com/videos/4_Arson009_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=lG3bH8Gm5LeRj8P4KGOa93IBxbs%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            5,
//            "Fighting",
//            96.5424,
//            16.5424,
//            73.4567,
//            "https://envision0177.s3.amazonaws.com/videos/5_Fighting.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=aUJbS07yw3EqIkmgQhVf9cSiovo%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            6,
//            "Robbery",
//            74.7474,
//            74.7474,
//            74.7474,
//            "https://envision0177.s3.amazonaws.com/videos/6_istockphoto-1338492601-640_adpp_is_1699117231391.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=mQROfsbKX3nKoKJParsp7E%2FqsWk%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            7,
//            "Robbery",
//            49.4949,
//            49.4949,
//            49.4949,
//            "https://envision0177.s3.amazonaws.com/videos/7_istockphoto-1338492601-640_adpp_is_1699117231391.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=alyKz35KhGRpWpC6i4l44BnLob0%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            8,
//            "Robbery",
//            63.0,
//            49.4949,
//            49.4949,
//            "https://envision0177.s3.amazonaws.com/videos/8_istockphoto-1338492601-640_adpp_is_1699117231391.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=bXHhz3If3d%2Fv%2FT2vpFq89Gf4CCM%3D&Expires=1708341792"
//        ),
//        CrimeValues(
//            9,
//            "Robbery",
//            25.2525,
//            25.2525,
//            25.2525,
//            "https://envision0177.s3.amazonaws.com/videos/9_1098961971-preview.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=cIuGMu7cWUrGnHUL3wI4JDJBph0%3D&Expires=1708341792"
//        )
//    )
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//android:layout_width="match_parent"
//android:layout_height="match_parent">
//
//<TextView
//android:id="@+id/titleTextView"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:text="MIL Validation"
//android:textSize="24sp"
//android:textStyle="bold"
//android:gravity="center"
//android:padding="16dp"
//android:background="#EEEEEE"/>
//
//<VideoView
//android:id="@+id/videoView"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_above="@id/approveButton"
//android:layout_below="@id/titleTextView"
//android:layout_marginTop="50dp"
//android:layout_marginBottom="200dp" />
//
//<Button
//android:id="@+id/approveButton"
//android:layout_width="match_parent"
//android:layout_height="50dp"
//android:layout_alignParentBottom="true"
//android:layout_marginStart="20dp"
//android:layout_marginEnd="20dp"
//android:layout_marginBottom="79dp"
//android:background="@drawable/btn_approve_bg"
//android:text="Approve"
//android:textColor="#FFFFFF"
//android:textColorHighlight="#4CAF50"
//android:textColorHint="#4CAF50"
//android:textColorLink="#4CAF50" />
//
//<Spinner
//android:id="@+id/disapproveDropdown"
//android:layout_width="match_parent"
//android:layout_height="50dp"
//android:layout_above="@id/approveButton"
//android:layout_marginStart="20dp"
//android:layout_marginTop="16dp"
//android:layout_marginEnd="20dp"
//android:layout_marginBottom="61dp"
//android:background="@drawable/btn_dropdown_bg"
//android:scrollbarSize="10dp" />
//
//<Button
//android:id="@+id/createDataButton"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_alignParentBottom="true"
//android:layout_centerHorizontal="true"
//android:layout_marginBottom="20dp"
//android:text="Create Data" />
//
//<TextView
//android:id="@+id/textView"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_above="@id/createDataButton"
//android:layout_centerHorizontal="true"
//android:layout_marginBottom="20dp" />
//
//</RelativeLayout>
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//package com.example.dynamo
//
//import android.os.Bundle
//import android.view.View
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import java.io.File
//import java.io.FileWriter
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var crimeData: CrimeData
//    private lateinit var crimeSpinner: Spinner
//    private lateinit var videoView: VideoView
//    private lateinit var approveButton: Button
//    private lateinit var disapproveDropdown: Spinner
//    private lateinit var textView: TextView
//    private lateinit var createDataButton: Button
//    private lateinit var crimeinfo: TextView
//
//    private var selectedCrime: CrimeData.CrimeValues? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        crimeData = CrimeData(resources)
//        crimeSpinner = findViewById(R.id.crimeSpinner)
//        videoView = findViewById(R.id.videoView)
//        approveButton = findViewById(R.id.approveButton)
//        disapproveDropdown = findViewById(R.id.disapproveDropdown)
//        textView = findViewById(R.id.textView)
//        createDataButton = findViewById(R.id.createDataButton)
//        crimeinfo = findViewById(R.id.crimeInfoTextView)
//
//        // Set up disapprove dropdown spinner
//        val disapproveItems = arrayOf("Disapprove", "Assault", "Arson", "Abuse", "Burglary", "Robbery")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, disapproveItems)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        disapproveDropdown.adapter = adapter
//
//        approveButton.setOnClickListener {
//            selectedCrime?.let { crime ->
//                storeVideoData(crime.video_id, "Approve")
//            }
//        }
//
//        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedAction = disapproveItems[position]
//                selectedCrime?.let { crime ->
//                    if (selectedAction != "Disapprove") {
//                        storeVideoData(crime.video_id, "Disapprove", selectedAction)
//                    }
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle the case when nothing is selected
//            }
//        }
//
//        createDataButton.setOnClickListener {
//            selectedCrime?.let { crime ->
//                storeVideoData(crime.video_id, "CreateData")
//            }
//        }
//
//        // Populate crime spinner
//        val videoIds = crimeData.dataCrimeList.map { it.video_id }
//        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, videoIds)
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        crimeSpinner.adapter = spinnerAdapter
//
//        // Set listener for spinner item selection
//        crimeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedVideoId = videoIds[position]
//                selectedCrime = crimeData.dataCrimeList.find { it.video_id == selectedVideoId }
//                selectedCrime?.let { displayCrimeData(it) }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle case when nothing is selected
//            }
//        }
//    }
//
//    private fun storeVideoData(videoId: Int, action: String, subclass: String = "") {
//        try {
//            val csvFile = File(filesDir, "video_data.csv")
//            val fileWriter = FileWriter(csvFile, true)
//
//            if (!csvFile.exists()) {
//                csvFile.createNewFile()
//                fileWriter.append("Video ID, Action, Subclass\n")
//            }
//
//            fileWriter.append("$videoId, $action, $subclass\n")
//            fileWriter.flush()
//            fileWriter.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun displayCrimeData(crime: CrimeData.CrimeValues) {
//        // Display the crime video in the videoView
//        videoView.setVideoPath(crime.video_url)
//        videoView.start()
//
//        // Display the fetched data in TextView
//        crimeinfo.text = "Video ID: ${crime.video_id}\nNature of Crime: ${crime.natureCrime}\nRisk Level: ${crime.Risklevel}\nLatitude: ${crime.latitude}\nLongitude: ${crime.longitude}"
//    }
//}

