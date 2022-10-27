package com.example.ca1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ca1.R

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar!!.title = "Slot Game"

        val totalSpinsVal = intent.getStringExtra(TOTAL_SPINS)
        val totalWinsVal = intent.getStringExtra(TOTAL_WINS)
        val totalWinRate = intent.getStringExtra(TOTAL_WIN_RATIO)
        Log.d("Lifecycle","onCreate() message = $totalSpinsVal")

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.numOfSpins).apply {
            text = "Total Spins $totalSpinsVal"
        }
        val textView1 = findViewById<TextView>(R.id.numberOfWins).apply {
            text = "Total Wins $totalWinsVal"
        }
        val textView2 = findViewById<TextView>(R.id.winSpinRatio).apply {
            text = "Total Win Rate $totalWinRate%"
        }
    }


}