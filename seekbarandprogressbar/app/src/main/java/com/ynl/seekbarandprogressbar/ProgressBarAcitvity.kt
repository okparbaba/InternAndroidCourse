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


        // when button is clicked, start the task
        button1.setOnClickListener { v ->
            // task is run on a thread
            Thread(Runnable {
                // dummy thread mimicking some operation whose progress cannot be tracked

                // display the indefinite progressbar
                this@ProgressBarAcitvity.runOnUiThread {
                    progressBar1.visibility = View.VISIBLE
                }

                // performing some dummy time taking operation
                try {
                    var i=0
                    while(i<Int.MAX_VALUE){
                        i++
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // when the task is completed, make progressBar gone
                this@ProgressBarAcitvity.runOnUiThread {
                    progressBar1.visibility = View.GONE
                }
            }).start()
        }
    }
}