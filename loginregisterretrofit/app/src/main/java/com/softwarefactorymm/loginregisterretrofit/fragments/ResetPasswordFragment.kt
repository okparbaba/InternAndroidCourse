package com.softwarefactorymm.loginregisterretrofit.fragments

import android.app.Fragment
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

import com.softwarefactorymm.loginregisterretrofit.R
import com.softwarefactorymm.loginregisterretrofit.models.User
import com.softwarefactorymm.loginregisterretrofit.server.RequestInterface
import com.softwarefactorymm.loginregisterretrofit.server.ServerRequest
import com.softwarefactorymm.loginregisterretrofit.server.ServerResponse
import com.softwarefactorymm.loginregisterretrofit.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResetPasswordFragment : Fragment(), View.OnClickListener {

    private var btn_reset: AppCompatButton? = null
    private var et_email: EditText? = null
    private var et_code: EditText? = null
    private var et_password: EditText? = null
    private var tv_timer: TextView? = null
    private var progress: ProgressBar? = null
    private var isResetInitiated = false
    private var email: String? = null
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_password_reset, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

        btn_reset = view.findViewById<View>(R.id.btn_reset) as AppCompatButton
        tv_timer = view.findViewById<View>(R.id.timer) as TextView
        et_code = view.findViewById<View>(R.id.et_code) as EditText
        et_email = view.findViewById<View>(R.id.et_email) as EditText
        et_password = view.findViewById<View>(R.id.et_password) as EditText
        et_password!!.visibility = View.GONE
        et_code!!.visibility = View.GONE
        tv_timer!!.visibility = View.GONE
        btn_reset!!.setOnClickListener(this)
        progress = view.findViewById<View>(R.id.progress) as ProgressBar

    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btn_reset ->
                if (!isResetInitiated) {

                    email = et_email!!.text.toString()
                    if (!email!!.isEmpty()) {
                        progress!!.visibility = View.VISIBLE
                        initiateResetPasswordProcess(email!!)
                    } else {

                        Snackbar.make(view!!, "Fields are empty !", Snackbar.LENGTH_LONG).show()
                    }
                } else {

                    val code = et_code!!.text.toString()
                    val password = et_password!!.text.toString()

                    if (!code.isEmpty() && !password.isEmpty()) {

                        finishResetPasswordProcess(email, code, password)
                    } else {

                        Snackbar.make(view!!, "Fields are empty !", Snackbar.LENGTH_LONG).show()
                    }

                }
        }
    }

    private fun initiateResetPasswordProcess(email: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requestInterface = retrofit.create(RequestInterface::class.java)

        val user = User()
        user.email = email
        val request = ServerRequest()
        request.setOperation(Constants.RESET_PASSWORD_INITIATE)
        request.setUser(user)
        val response = requestInterface.operation(request)

        response.enqueue(object : Callback<ServerResponse> {
            override fun onResponse(call: Call<ServerResponse>, response: retrofit2.Response<ServerResponse>) {

                val resp = response.body()
                Snackbar.make(view, resp?.message+"", Snackbar.LENGTH_LONG).show()

                if (resp?.result.equals(Constants.SUCCESS)) {

                    Snackbar.make(view, resp?.result+"", Snackbar.LENGTH_LONG).show()
                    et_email!!.visibility = View.GONE
                    et_code!!.visibility = View.VISIBLE
                    et_password!!.visibility = View.VISIBLE
                    tv_timer!!.visibility = View.VISIBLE
                    btn_reset!!.text = "Change Password"
                    isResetInitiated = true
                    startCountdownTimer()

                } else {

                    Snackbar.make(view, resp?.message+"", Snackbar.LENGTH_LONG).show()

                }
                progress!!.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {

                progress!!.visibility = View.INVISIBLE
                Log.d(Constants.TAG, "failed")
                Snackbar.make(view!!, t.localizedMessage, Snackbar.LENGTH_LONG).show()

            }
        })
    }

    private fun finishResetPasswordProcess(email: String?, code: String, password: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requestInterface = retrofit.create(RequestInterface::class.java)

        val user = User()
        user.email = email
        user.setCode(code)
        user.setPassword(password)
        val request = ServerRequest()
        request.setOperation(Constants.RESET_PASSWORD_FINISH)
        request.setUser(user)
        val response = requestInterface.operation(request)

        response.enqueue(object : Callback<ServerResponse> {
            override fun onResponse(call: Call<ServerResponse>, response: retrofit2.Response<ServerResponse>) {

                val resp = response.body()
                Snackbar.make(view, resp!!.message!!, Snackbar.LENGTH_LONG).show()

                if (resp.result.equals(Constants.SUCCESS)) {

                    Snackbar.make(view, resp.message!!, Snackbar.LENGTH_LONG).show()
                    countDownTimer!!.cancel()
                    isResetInitiated = false
                    goToLogin()

                } else {

                    Snackbar.make(view, resp.message!!, Snackbar.LENGTH_LONG).show()

                }
                progress!!.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {

                progress!!.visibility = View.INVISIBLE
                Log.d(Constants.TAG, "failed")
                Snackbar.make(view!!, t.localizedMessage, Snackbar.LENGTH_LONG).show()

            }
        })
    }

    private fun startCountdownTimer() {
        countDownTimer = object : CountDownTimer(120000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tv_timer!!.text = "Time remaining : " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                Snackbar.make(view!!, "Time Out ! Request again to reset password.", Snackbar.LENGTH_LONG).show()
                goToLogin()
            }
        }.start()
    }

    private fun goToLogin() {

        val login = LoginFragment()
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_frame, login)
        ft.commit()
    }
}