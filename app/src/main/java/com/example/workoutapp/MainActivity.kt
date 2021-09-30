package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.llstartbtn.setOnClickListener {
            startActivity(Intent(this,ExerciseActivity::class.java))
        }
        binding.llBMI.setOnClickListener {
            startActivity(Intent(this,BMIActivity::class.java))
        }
        binding.llHistory.setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }

    }
}