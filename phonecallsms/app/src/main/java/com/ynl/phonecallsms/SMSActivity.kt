package com.ynl.phonecallsms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sms.*

class SMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_sendsms.setOnClickListener { requestSMSPermission() }
    }

    private fun SendSMS() {
        val smsPho = et_phonenum.text.toString()
        val smsBody = et_sendbody.text.toString()
        sendSMS(smsPho, smsBody)
    }

    private fun sendSMS(MobileNo: String, SMSBody: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(MobileNo, null, SMSBody, null, null)
            Toast.makeText(this@SMSActivity, "Sended Successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this@SMSActivity, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun requestSMSPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                    requestPermissions(arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST)
                } else {
                    requestPermissions(arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST)
                }
            } else {
                SendSMS()
            }
        } else {
            SendSMS()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@SMSActivity, "permissiion granted", Toast.LENGTH_SHORT).show()
            SendSMS()
        } else {
            Toast.makeText(this@SMSActivity, "permissiion denied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private val PERMISSION_REQUEST = 198
    }
}
