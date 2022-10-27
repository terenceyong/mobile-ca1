package com.example.ca1

import  android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ca1.Wheel.WheelListener
import java.util.*
import kotlin.math.roundToInt

const val TOTAL_SPINS = "TotalSpins"
const val TOTAL_WINS = "TotalWins"
const val TOTAL_WIN_RATIO = "WinRate"

class MainActivity : AppCompatActivity() {
    private var msg: TextView? = null
    private var numOfSpins: TextView? = null
    private var numberOfWins: TextView? = null
    private var winSpinRatio: TextView? = null

    private var wheel1: Wheel? = null
    private var wheel2: Wheel? = null
    private var wheel3: Wheel? = null
    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null
    private var isStarted = false
    private var numberOfClicks = 0
    private var numberOfW = 0
    private var winSpin = 0
    private var img1: ImageView? = null
    private var img2: ImageView? = null
    private var img3: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Slot Game"


        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        msg = findViewById(R.id.msg)
        numOfSpins = findViewById(R.id.numOfSpins)
        numberOfWins = findViewById(R.id.numberOfWins)
        winSpinRatio = findViewById(R.id.winSpinRatio)

        btn1!!.setOnClickListener {
            numberOfClicks++
            numOfSpins!!.setText("Number of Spins " + numberOfClicks)
            if (isStarted) {
                wheel1!!.stopWheel()
                wheel2!!.stopWheel()
                wheel3!!.stopWheel()
                if (wheel1!!.currentIndex == wheel2!!.currentIndex && wheel2!!.currentIndex == wheel3!!.currentIndex) {
                    msg!!.text = "Winner"
                    numberOfW++
                    winSpin = ((numberOfW.toDouble() / numberOfClicks.toDouble()) *100).roundToInt()
                    winSpinRatio!!.setText("winSpinRatio " + winSpin+"%")
                } else {
                    msg!!.text = "Bad luck"
                }
                numberOfWins!!.setText("Number of Wins " + numberOfW)
                btn1!!.text = "Start"
                isStarted = false
            }

            else {
                wheel1 = Wheel(object : WheelListener {
                    override fun newImage(img: Int) {
                        runOnUiThread { img1!!.setImageResource(img) }
                    }
                }, 200, randomLong(0, 200))
                wheel1!!.start()
                wheel2 = Wheel(object : WheelListener {
                    override fun newImage(img: Int) {
                        runOnUiThread { img2!!.setImageResource(img) }
                    }
                }, 200, randomLong(150, 400))
                wheel2!!.start()
                wheel3 = Wheel(object : WheelListener {
                    override fun newImage(img: Int) {
                        runOnUiThread { img3!!.setImageResource(img) }
                    }
                }, 200, randomLong(150, 400))
                wheel3!!.start()
                btn1!!.text = "Stop"
                msg!!.text = ""
                isStarted = true
            }
            Log.d("Lifecycle", "Spin... begining()")
        }
        btn2!!.setOnClickListener { openActivity2() }
        btn3!!.setOnClickListener { openActivity3() }
    }

    private fun openActivity2() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    private fun openActivity3() {
        Log.d("Lifecycle", "openActivity3... begining()")
        val intent = Intent(this, MainActivity3::class.java).apply {
            putExtra(TOTAL_SPINS, numberOfClicks.toString())
            putExtra(TOTAL_WINS, numberOfW.toString())
            putExtra(TOTAL_WIN_RATIO, winSpin.toString())

        }
        startActivity(intent)
    }


    companion object {
        val RANDOM = Random()
        fun randomLong(lower: Long, upper: Long): Long {
            return lower + (RANDOM.nextDouble() * (upper - lower)).toLong()
        }
    }
}