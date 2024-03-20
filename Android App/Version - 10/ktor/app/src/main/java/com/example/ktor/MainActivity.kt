package com.example.ktor
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CopyObjectRequest
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MyApp

fun main(args: Array<String>) {
    runApplication<MyApp>(*args)
}

@RestController
class MyController {

    val awsAccessKeyId = "YOUR_AWS_ACCESS_KEY_ID"
    val awsSecretAccessKey = "YOUR_AWS_SECRET_ACCESS_KEY"
    val awsRegion = Regions.AP_SOUTH_1
    val dynamoDBTableName = "test"
    val s3BucketName = "test1770"

    val dynamoDB: AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
        .withRegion(awsRegion)
        .withCredentials(BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey))
        .build()

    val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
        .withRegion(awsRegion)
        .withCredentials(BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey))
        .build()

    val dynamoDBInstance = DynamoDB(dynamoDB)
    val table: Table = dynamoDBInstance.getTable(dynamoDBTableName)

    @PostMapping("/approve_video")
    fun approveVideo(@RequestParam videoId: String): ResponseEntity<String> {
        val response = table.getItem("video_id", videoId)
        val item = response.item
        val natureOfCrime = item["nature_crime"] as String? ?: "Unknown"
        val oldKey = "videos/${videoId}_${item["video"]["name"]}"
        val newKey = "approved_videos/$natureOfCrime/${videoId}_${item["video"]["name"]}"
        val copyObjectRequest = CopyObjectRequest(s3BucketName, oldKey, s3BucketName, newKey)
        s3Client.copyObject(copyObjectRequest)
        val updateItemRequest = UpdateItemRequest()
            .withTableName(dynamoDBTableName)
            .addKeyEntry("video_id", AttributeValue(videoId))
            .updateExpression("SET #status_col = :status_val")
            .expressionAttributeNames(mapOf("#status_col" to "status"))
            .expressionAttributeValues(mapOf(":status_val" to AttributeValue("Approved")))
        dynamoDB.updateItem(updateItemRequest)
        return ResponseEntity.ok("Video has been approved.")
    }

    @PostMapping("/disapprove_video")
    fun disapproveVideo(@RequestParam videoId: String, @RequestParam disapproveReason: String): ResponseEntity<String> {
        val response = table.getItem("video_id", videoId)
        val item = response.item
        val oldKey = "videos/${videoId}_${item["video"]["name"]}"
        val newKey = "disapproved_videos/$disapproveReason/${videoId}_${item["video"]["name"]}"
        val copyObjectRequest = CopyObjectRequest(s3BucketName, oldKey, s3BucketName, newKey)
        s3Client.copyObject(copyObjectRequest)
        val updateItemRequest = UpdateItemRequest()
            .withTableName(dynamoDBTableName)
            .addKeyEntry("video_id", AttributeValue(videoId))
            .updateExpression("SET #status_col = :status_val, disapprove_reason = :disapprove_reason")
            .expressionAttributeNames(mapOf("#status_col" to "status"))
            .expressionAttributeValues(mapOf(
                ":status_val" to AttributeValue("Disapproved"),
                ":disapprove_reason" to AttributeValue(disapproveReason)
            ))
        dynamoDB.updateItem(updateItemRequest)
        return ResponseEntity.ok("Video has been disapproved.")
    }
}
