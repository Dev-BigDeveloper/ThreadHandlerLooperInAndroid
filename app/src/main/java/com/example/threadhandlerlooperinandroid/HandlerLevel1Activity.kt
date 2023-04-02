package com.example.threadhandlerlooperinandroid

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.forEach
import com.example.threadhandlerlooperinandroid.databinding.ActivityHandlerLevel1Binding
import kotlin.random.Random

class HandlerLevel1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHandlerLevel1Binding

    private val handler = Handler(Looper.getMainLooper())

    private val token = Any()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_level1)

        binding = ActivityHandlerLevel1Binding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.root.forEach {
            if (it is Button) it.setOnClickListener(universalButtonListener)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun toggleTestButtonState() {
        binding.testButton.isEnabled = !binding.testButton.isEnabled
    }

    private fun nextRandomColor() {
        val randomColor = -Random.nextInt(255 * 255 * 255)
        binding.colorView.setBackgroundColor(randomColor)
    }

    private fun showToast() {
        Toast.makeText(this, R.string.hello, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val universalButtonListener = View.OnClickListener {
        Thread {
            when (it.id) {

                R.id.enableDisableButton ->
                    handler.post { toggleTestButtonState() }
                R.id.randomColorButton ->
                    handler.post { nextRandomColor() }

                R.id.enableDisableDelayedButton ->
                    handler.postDelayed({ toggleTestButtonState()}, DELAY)
                R.id.randomColorDelayedButton ->
                    handler.postDelayed({ nextRandomColor() }, DELAY)

                R.id.randomColorTokenDelayedButton ->
                    handler.postDelayed({ toggleTestButtonState()}, token, DELAY)
                R.id.showToastButton ->
                    handler.postDelayed({ showToast() }, token, DELAY)
                R.id.cancelButton -> handler.removeCallbacksAndMessages(token)

            }
        }.start()
    }

    companion object {
        @JvmStatic private val DELAY = 2000L // milliseconds
    }

}