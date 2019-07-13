package com.ynl.intentandactivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Hello")
        }
        texttt.setOnClickListener {
            startActivity(sendIntent)
        }

        textView.setOnClickListener {
            val int = Intent(this@MainActivity,Main2Activity::class.java)
            int.putExtra("id","123")
            startActivity(int)
        }

    }
}
