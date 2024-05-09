package com.example.dynamo

//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Button
//import android.widget.Spinner
//import androidx.appcompat.app.AppCompatActivity
//import java.io.File
//import java.io.FileWriter
//import android.widget.VideoView
//import android.widget.TextView
//import android.widget.Toast
//
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
//            createCSV()
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
//                val selectedCrime = crimeData.dataCrimeList.find { it.video_id == selectedVideoId }
//                selectedCrime?.let { displayCrimeData(it) }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle case when nothing is selected
//            }
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
//    private fun createCSV() {
//        try {
//            val csvFile = File(filesDir, "crime_data.csv")
//            val fileWriter = FileWriter(csvFile)
//
//            // Write header to the CSV file
//            fileWriter.append("video_id, nature_crime, risk_level, latitude, longitude, video_link, approve/disapprove, subclass if disapprove\n")
//
//            // Iterate through each crime entry and write it to the CSV file
//            for (crime in crimeData.dataCrimeList) {
//                val entry = "${crime.video_id}, ${crime.natureCrime}, ${crime.Risklevel}, ${crime.latitude}, ${crime.longitude}, ${crime.video_url}, ${crime.approvalStatus}, ${crime.subclass}\n"
//                fileWriter.append(entry)
//            }
//
//            // Flush and close the file writer
//            fileWriter.flush()
//            fileWriter.close()
//
//            Toast.makeText(this, "CSV file created successfully", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Toast.makeText(this, "Failed to create CSV file", Toast.LENGTH_SHORT).show()
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
//        val video_url: String,
//
//        ) {
//        var approvalStatus: String = "Pending"
//        var subclass: String = ""
//
//        fun approve() {
//            approvalStatus = "Approved"
//        }
//
//        fun disapprove(subclass: String) {
//            approvalStatus = "Disapproved"
//            this.subclass = subclass
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
//            "https://envision0177.s3.amazonaws.com/videos/1_Fighting049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=e51ALWQCZLzbKpoUqGK0stwyL0M%3D&Expires=1708341792",
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


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
//    private lateinit var createDataButton: Button
//    private lateinit var crimeinfo: TextView
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
//            val selectedVideoId = crimeSpinner.selectedItem.toString().toInt()
//            storeVideoData(selectedVideoId, "Approve")
//        }
//
//        disapproveDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedAction = disapproveItems[position]
//                if (selectedAction != "Disapprove") {
//                    val selectedVideoId = crimeSpinner.selectedItem.toString().toInt()
//                    storeVideoData(selectedVideoId, "Disapprove", selectedAction)
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle the case when nothing is selected
//            }
//        }
//
//        createDataButton.setOnClickListener {
//            createCSV()
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
//                val selectedCrime = crimeData.dataCrimeList.find { it.video_id == selectedVideoId }
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
//            val csvFile = File(filesDir, "crime_data.csv")
//            val fileWriter = FileWriter(csvFile, true)
//
//            if (!csvFile.exists()) {
//                csvFile.createNewFile()
//                fileWriter.append("video_id, nature_crime, risk_level, latitude, longitude, video_link, approve/disapprove, subclass if disapprove\n")
//            }
//
//            val crime = crimeData.dataCrimeList.find { it.video_id == videoId }
//            crime?.let {
//                it.approveStatus = action
//                it.subclass = subclass
//            }
//
//            // Write data to CSV
//            crime?.let {
//                val entry = "${it.video_id}, ${it.natureCrime}, ${it.Risklevel}, ${it.latitude}, ${it.longitude}, ${it.video_url}, ${it.approveStatus}, ${it.subclass}\n"
//                fileWriter.append(entry)
//            }
//
//            fileWriter.flush()
//            fileWriter.close()
//
//            Toast.makeText(this, "CSV file updated successfully", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Toast.makeText(this, "Failed to update CSV file", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun createCSV() {
//        try {
//            val csvFile = File(filesDir, "crime_data.csv")
//            val fileWriter = FileWriter(csvFile)
//
//            // Write header to the CSV file
//            fileWriter.append("video_id, nature_crime, risk_level, latitude, longitude, video_link, approve/disapprove, subclass if disapprove\n")
//
//            // Iterate through each crime entry and write it to the CSV file
//            for (crime in crimeData.dataCrimeList) {
//                val entry = "${crime.video_id}, ${crime.natureCrime}, ${crime.Risklevel}, ${crime.latitude}, ${crime.longitude}, ${crime.video_url}, Pending, \n"
//                fileWriter.append(entry)
//            }
//
//            // Flush and close the file writer
//            fileWriter.flush()
//            fileWriter.close()
//
//            Toast.makeText(this, "CSV file created successfully", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Toast.makeText(this, "Failed to create CSV file", Toast.LENGTH_SHORT).show()
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





