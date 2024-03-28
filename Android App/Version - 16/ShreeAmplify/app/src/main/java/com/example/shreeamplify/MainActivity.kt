package com.example.shreeamplify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amplifyframework.core.Amplify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Amplify.configure(getApplicationContext())
    }
}

