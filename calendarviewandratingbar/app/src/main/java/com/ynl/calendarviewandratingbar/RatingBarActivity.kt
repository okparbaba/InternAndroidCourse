package com.ynl.calendarviewandratingbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class RatingBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ratingbar)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        if (ratingBar != null) {
            val button = findViewById<Button>(R.id.button)
            button?.setOnClickListener {
                val msg = ratingBar.rating.toString()
                Toast.makeText(this@RatingBarActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
