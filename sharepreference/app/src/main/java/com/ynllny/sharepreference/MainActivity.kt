package com.ynllny.sharepreference

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("id", 1)
            putString("ok","Hello")
            apply()
        }
        val id = sharedPref.getString("ok","jhkl")
        Log.e("id",id.toString())

    }
}
