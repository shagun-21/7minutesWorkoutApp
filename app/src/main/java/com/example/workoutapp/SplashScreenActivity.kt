package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    val splash_time_out:Long=2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        dumbellLottie.animate().translationY(1400f).setDuration(1000).setStartDelay(4000)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },splash_time_out)
    }
}