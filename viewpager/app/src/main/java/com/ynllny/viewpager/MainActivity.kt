package com.ynllny.viewpager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //private var currentPage = 0
    private val IMAGES = arrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3)
    private val ImagesArray = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        for (i in 0 until IMAGES.size)
            ImagesArray.add(IMAGES[i])
        pager.adapter = SlidingImage_Adapter(this@MainActivity, ImagesArray)
    }
}
//
//        // Auto start of viewpager
//        val handler = Handler()
//        val Update = Runnable {
//            if (currentPage === NUM_PAGES) {
//                currentPage = 0
//            }
//            pager.setCurrentItem(currentPage++, true)
//        }
//        val swipeTimer = Timer()
//        swipeTimer.schedule(object : TimerTask() {
//            override fun run() {
//                handler.post(Update)
//            }
//        }, 3000, 3000)

