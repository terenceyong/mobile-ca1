package com.example.ca1

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.ca1.Wheel
import android.os.Bundle
import com.example.ca1.R
import com.example.ca1.Wheel.WheelListener
import com.example.ca1.MainActivity
import android.content.Intent
import com.example.ca1.MainActivity2

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar!!.title = "Slot Game"
    }
}