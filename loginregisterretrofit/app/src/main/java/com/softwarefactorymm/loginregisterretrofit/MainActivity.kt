package com.softwarefactorymm.loginregisterretrofit

import android.app.Fragment
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.softwarefactorymm.loginregisterretrofit.fragments.LoginFragment
import com.softwarefactorymm.loginregisterretrofit.fragments.ProfileFragment
import com.softwarefactorymm.loginregisterretrofit.utils.Constants

class MainActivity : AppCompatActivity() {

    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getPreferences(0)
        initFragment()
    }

    private fun initFragment() {
        val fragment: Fragment
        if (pref?.getBoolean(Constants.IS_LOGGED_IN, false)!!) {
            fragment = ProfileFragment()
        } else {
            fragment = LoginFragment()
        }
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_frame, fragment)
        ft.commit()
    }

}