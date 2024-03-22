package com.example.connection

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CopyObjectRequest
import software.amazon.awssdk.services.s3.model.S3Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize AWS credentials
        val dynamoDbCredentials = AwsBasicCredentials.create("AccessKey", "SecretAccessKey")
        val s3Credentials = AwsBasicCredentials.create("AccessKey", "SecretAccessKey")

        // Initialize DynamoDB and S3 clients
        val dynamoDbClient = DynamoDbClient.builder()
            .region(Region.AP_SOUTH_1)
            .credentialsProvider(StaticCredentialsProvider.create(dynamoDbCredentials))
            .build()

        val s3Client = S3Client.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(s3Credentials))
            .build()

        // Sample video ID
        val videoId = "#vidid"

        // Assuming "approve" button click
        GlobalScope.launch(Dispatchers.IO) {
            copyVideo(videoId, "approve_videos/$videoId", s3Client)
        }

        // Assuming "disapprove" button click
        GlobalScope.launch(Dispatchers.IO) {
            copyVideo(videoId, "disapprove_videos/$videoId", s3Client)
        }
    }

    private suspend fun copyVideo(videoId: String, destinationKey: String, s3Client: S3Client) {
        val copyRequest = CopyObjectRequest.builder()
            .copySource("#vidid/$videoId")
            .destinationBucket("#vidid0")
            .destinationKey(destinationKey)
            .build()
        try {
            s3Client.copyObject(copyRequest)
            Log.d("CopyVideo", "Video copied successfully to $destinationKey")
        } catch (e: S3Exception) {
            Log.e("CopyVideo", "Error copying object: ${e.message}")
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Error copying object: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

