package com.example.threadhandlerlooperinandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.threadhandlerlooperinandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.handlerLevel1Button.setOnClickListener {
            startActivity(Intent(this, HandlerLevel1Activity::class.java))
        }

        binding.handlerLevel2Button.setOnClickListener {
            startActivity(Intent(this, HandlerLevel2Activity::class.java))
        }

    }
}