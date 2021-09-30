package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {



    private val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    private val US_UNITS_VIEW = "US_UNIT_VIEW"
    private var currentVisibleView: String = METRIC_UNITS_VIEW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        setSupportActionBar(toolbar_bmi_activity)

        val actionBar=supportActionBar
        if (actionBar !==null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title="CALCULATE BMI"
        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        btnCalculateUnits.setOnClickListener {
            when (currentVisibleView) {
                METRIC_UNITS_VIEW -> calculateMetricUnitsBMI()
                US_UNITS_VIEW -> calculateUsUnitsBMI()
            }
        }

        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMetricUnits -> makeVisibleMetricUnitsView()
                R.id.rbUsUnits -> makeVisibleUsUnitsView()
            }
        }
    }

    private fun calculateUsUnitsBMI() {
        if (validateUsUnits()) {
            val feetValue: Float = etUsUnitHeightFeet.text.toString().toFloat()
            val inchValue: Float = etUsUnitHeightInch.text.toString().toFloat()

            val heightValue: Float = inchValue + feetValue * 12
            val weightValue: Float = etUsUnitWeight.text.toString().toFloat()

            val bmi = 703 * (weightValue / (heightValue * heightValue))
            displayBMIResult(bmi)
        } else {
            Toast.makeText(this, "Please enter valid values!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateMetricUnitsBMI() {
        if (validateMetricUnits()) {
            val heightValue: Float = etMetricUnitHeight.text.toString().toFloat() / 100
            val weightValue: Float = etMetricUnitWeight.text.toString().toFloat()

            val bmi = weightValue / (heightValue * heightValue)
            displayBMIResult(bmi)
        } else {
            Toast.makeText(this, "Please enter valid values!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateUsUnits(): Boolean {
        return when {
            etUsUnitWeight.text.toString().isEmpty() -> false
            etUsUnitHeightFeet.text.toString().isEmpty() -> false
            etUsUnitHeightInch.text.toString().isEmpty() -> false
            else -> true
        }
    }

    private fun validateMetricUnits(): Boolean {
        return when {
            etMetricUnitWeight.text.toString().isEmpty() -> false
            etMetricUnitHeight.text.toString().isEmpty() -> false
            else -> true
        }
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription

        llDisplayBMIResult.visibility = View.VISIBLE
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW

        tilMetricUnitWeight.visibility = View.VISIBLE
        tilMetricUnitHeight.visibility = View.VISIBLE

        tilUsUnitWeight.visibility = View.GONE
        llUsUnitsHeight.visibility = View.GONE

        llDisplayBMIResult.visibility = View.GONE

        etMetricUnitHeight.text?.clear()
        etMetricUnitWeight.text?.clear()
    }

    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW

        tilMetricUnitWeight.visibility = View.GONE
        tilMetricUnitHeight.visibility = View.GONE

        tilUsUnitWeight.visibility = View.VISIBLE
        llUsUnitsHeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.GONE

        etUsUnitWeight.text?.clear()
        etUsUnitHeightFeet.text?.clear()
        etUsUnitHeightInch.text?.clear()
    }

}
