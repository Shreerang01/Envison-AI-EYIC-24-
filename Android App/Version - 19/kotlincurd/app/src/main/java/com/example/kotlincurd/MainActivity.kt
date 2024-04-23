package com.example.kotlincurd

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoDatabase
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = getDatabase()

        database.listCollectionNames().collect
    }

    private fun getDatabase() :MongoDatabase{

        val client = MongoClient.create(connectionString  = "")

        val connectionString = "mongodb+srv://shreerangandroid:booba123@cluster0.qasrtdt.mongodb.net/"
        return client.getDatabase(databaseName = "crime")
    }
}
