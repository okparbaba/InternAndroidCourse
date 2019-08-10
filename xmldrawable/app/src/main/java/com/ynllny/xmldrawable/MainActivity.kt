package com.ynllny.xmldrawable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hyperspaceJump: Animation = AnimationUtils.loadAnimation(this, R.anim.test)
        bu.startAnimation(hyperspaceJump)

    }
}
