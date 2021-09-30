package com.example.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_back_button.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() ,TextToSpeech.OnInitListener {

    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var restTimerDuration:Long=10

    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0
    private val exerciseTimerDuration = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition: Int = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar=supportActionBar
        if (actionbar !=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_exercise_activity.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        tts= TextToSpeech(this,this)

        exerciseList=Constants.defaultExerciseList()
        setupRestView()

        setupExerciseStatusRecyclerView()
    }
    override fun onDestroy() {


        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }

        super.onDestroy()
    }
    private fun setRestProgressbar(){
        progressBar.progress=restProgress
        restTimer=object:CountDownTimer(restTimerDuration*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress=10-restProgress
                tvTimer.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()

            }
        }.start()
    }

    private fun setExerciseProgressbar(){
        progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration*1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration - exerciseProgress
                tvExerciseTimer.text = (exerciseTimerDuration - exerciseProgress).toString()
            }

            override fun onFinish() {

                if (currentExercisePosition < exerciseList?.size!!-1 ) {
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else{
                    finish()
                    startActivity(Intent(this@ExerciseActivity,FinishActivity::class.java))
                }
            }
        }.start()
    }

    private fun setupRestView(){

        try {
            player = MediaPlayer.create(applicationContext, R.raw.pop_up_sound)
            player!!.isLooping = false
            player!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        llRestView.visibility=View.VISIBLE
        llExerciseView.visibility=View.GONE

        if (restTimer !=null){
            restTimer!!.cancel()
            restProgress=0
        }
        tvUpcomingExerciseName.text=exerciseList!![currentExercisePosition+1].getName()
        setRestProgressbar()

    }
    private fun setupExerciseView(){

        llRestView.visibility= View.GONE
        llExerciseView.visibility=View.VISIBLE


        if (exerciseTimer !=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressbar()

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text=exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization failed!")
        }
    }
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    private fun setupExerciseStatusRecyclerView(){
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)

        rvExerciseStatus.adapter = exerciseAdapter
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
    }
    private fun customDialogForBackButton() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_back_button)

        dialog.btnYes.setOnClickListener {
            finish()
            dialog.dismiss()
        }

        dialog.btnNo.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}