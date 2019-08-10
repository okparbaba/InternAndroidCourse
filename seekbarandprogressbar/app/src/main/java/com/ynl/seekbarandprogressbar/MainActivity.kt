package com.ynl.seekbarandprogressbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("Registered")
class MainActivity : AppCompatActivity(), OnSeekBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekbar!!.setOnSeekBarChangeListener(this)

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        tvprogress.text = progress.toString()
        tvseekbarStatus.text = "Tracking"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        tvseekbarStatus.text = "Start Tracking"
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        tvseekbarStatus.text = "Stop Tracking"
    }
}
