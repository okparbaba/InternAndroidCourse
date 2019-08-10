package com.softwarefactorymm.loginregister

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.softwarefactorymm.loginregister.server.ServerRequest.Companion.retrofitClient
import com.softwarefactorymm.loginregister.server.ServerRespone
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btResigter.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            progressBar.visibility = View.VISIBLE
            val webClient = retrofitClient.register(name,email,password)
            webClient.enqueue(object:Callback<ServerRespone>{
                override fun onFailure(call: Call<ServerRespone>, t: Throwable) {
                    Log.e("register",t.message.toString())
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<ServerRespone>, response: Response<ServerRespone>) {
                    Log.e("register",response.body().toString())
                    progressBar.visibility = View.GONE
                    val res = response.body()
                    val message = res?.data?.message
                    t("$message")
                }

            })

        }
    }
    fun Context.t(s:String){
        Toast.makeText(applicationContext,s,Toast.LENGTH_SHORT).show()
    }
}
