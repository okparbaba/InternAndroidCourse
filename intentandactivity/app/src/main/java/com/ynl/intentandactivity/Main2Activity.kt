package com.ynl.intentandactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val id = intent.getStringExtra("id")
        Toast.makeText(this@Main2Activity,id,Toast.LENGTH_LONG).show()
        Log.e("TestOne","Hello")
    }
}
