package com.example.ca1

import com.example.ca1.Wheel.WheelListener
import com.example.ca1.Wheel
import com.example.ca1.R

class Wheel(
    private val wheelListener: WheelListener?,
    private val frameDuration: Long,
    private val startIn: Long
) : Thread() {
    interface WheelListener {
        fun newImage(img: Int)
    }

    @JvmField
    var currentIndex = 0
    private var isStarted = true
    fun nextImg() {
        currentIndex++
        if (currentIndex == imgs.size) {
            currentIndex = 0
        }
    }

    override fun run() {
        try {
            sleep(startIn)
        } catch (e: InterruptedException) {
        }
        while (isStarted) {
            try {
                sleep(frameDuration)
            } catch (e: InterruptedException) {
            }
            nextImg()
            wheelListener?.newImage(imgs[currentIndex])
        }
    }

    fun stopWheel() {
        isStarted = false
    }

    companion object {
        private val imgs =
            intArrayOf(R.drawable.dog5, R.drawable.dog6, R.drawable.dog3, R.drawable.dog4)
    }
}