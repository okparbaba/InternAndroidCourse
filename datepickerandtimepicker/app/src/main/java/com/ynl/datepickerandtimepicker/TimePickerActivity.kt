package com.ynl.datepickerandtimepicker

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.timepicker.*

@SuppressLint("Registered")
class TimePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timepicker)
        getSelectedTime()
    }

    private fun getSelectedTime() {
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            var hourOfDay = hourOfDay
            val format: String
            when {
                hourOfDay == 0 -> {
                    hourOfDay += 12
                    format = "AM"
                }
                hourOfDay == 12 -> format = "PM"
                hourOfDay > 12 -> {
                    hourOfDay -= 12
                    format = "PM"
                }
                else -> format = "AM"
            }
            if (textView != null) {
                val hour = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                val min = if (minute < 10) "0$minute" else minute

                val text = "SelectedTime $hour : $min $format"
                textView.text = text
                textView.visibility = View.VISIBLE
            }
        }
    }
}
