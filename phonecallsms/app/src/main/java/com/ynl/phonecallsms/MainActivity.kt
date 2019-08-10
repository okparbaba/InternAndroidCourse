package com.ynl.phonecallsms

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

@SuppressLint("Registered")
class MainActivity : AppCompatActivity() {

    private var twice: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_dial.setOnClickListener {
            val number = et_number.text.toString()
            val call = Uri.parse("tel:$number")
            val surr = Intent(Intent.ACTION_DIAL, call)
            startActivity(surr)
        }
        bt_call.setOnClickListener {
            if (checkPermission(Manifest.permission.CALL_PHONE)) {
                val number2 = et_number.text.toString()
                val num2 = Uri.parse("tel:$number2")
                val sudd = Intent(Intent.ACTION_CALL, num2)
                startActivity(sudd)
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MAKE_CALL_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun checkPermission(Permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, Permission) == PackageManager.PERMISSION_GRANTED
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MAKE_CALL_PERMISSION_REQUEST_CODE -> {
                if (grantResults.run { isNotEmpty() } && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this@MainActivity, "you can call by clicking button",
                    Toast.LENGTH_SHORT).show()
            }
        }
        return
    }

    override fun onBackPressed() {
        Log.d("Tag", "click")
        if (twice) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        Log.d("Tag", "Twice$twice")
        twice = true
        // super.onBackPressed();
        Toast.makeText(this@MainActivity, "ထြက္ရန္ေနာက္တစ္ခါႏွိပ္ပါ။။......", Toast.LENGTH_LONG).show()
        Handler().postDelayed({
            Log.d("Tag", "Twice :$twice")
            twice = false
        }, 3000)
        //super.onBackPressed();
    }

    companion object {
        private val MAKE_CALL_PERMISSION_REQUEST_CODE = 1
    }
}
