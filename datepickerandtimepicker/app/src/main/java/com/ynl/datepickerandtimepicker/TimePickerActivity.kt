package com.ynl.datepickerandtimepicker

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.timepicker.*

@SuppressLint("Registered")
class TimePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timepicker)
        selectedDate()
    }

    private fun selectedDate(){
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            var hourOfDay = hourOfDay
            val ourFormat:String
            when{
                hourOfDay == 0 ->{
                    hourOfDay += 12
                    ourFormat = "AM"
                }
                hourOfDay == 12 -> ourFormat = "PM"
                hourOfDay >12 ->{
                    hourOfDay -= 12
                    ourFormat = "PM"
                }
                else -> ourFormat = "AM"
            }
            if (textView !=null){
                val hour = if (hourOfDay <10) "0$hourOfDay" else hourOfDay
                val min = if (minute <10) "0$minute" else minute
                val text = "Selected Time $hour: $min $ourFormat"
                textView.text = text
            }
        }
    }

}
