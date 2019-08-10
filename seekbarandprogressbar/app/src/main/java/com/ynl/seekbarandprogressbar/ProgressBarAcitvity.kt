package com.ynl.seekbarandprogressbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.progressbar.*

@SuppressLint("Registered")
class ProgressBarAcitvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progressbar)
        button.setOnClickListener {
            Thread(Runnable {
                this@ProgressBarAcitvity.runOnUiThread {
                    progressBar.visibility = View.VISIBLE
                }
                try {
                    var i = 0
                    while (i<1000000000){
                        i++
                    }
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
                this@ProgressBarAcitvity.runOnUiThread {
                    progressBar.visibility = View.GONE
                }
            }).start()
        }
    }
}