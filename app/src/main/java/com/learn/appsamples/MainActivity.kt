package com.learn.appsamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.appsamples.databinding.ActivityMainBinding
import com.learn.appsamples.oneTimeEvents.OneTimeEventActivity

class MainActivity : AppCompatActivity() {

    // Kotlinx
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.oneTimeEventBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, OneTimeEventActivity::class.java))
        }
    }
}