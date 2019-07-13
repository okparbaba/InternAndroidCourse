package com.ynllny.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (fragment_container != null) {
            val firstFragment = FirstFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, firstFragment)
                .commit()
        }
        btChangeFragNext.setOnClickListener {
            val newFragment = SecondFragment()
            val args = Bundle()
            args.putInt("some",34)
            newFragment.arguments = args
            val transaction = supportFragmentManager.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                replace(R.id.fragment_container, newFragment)
                //addToBackStack(null)
            }

            // Commit the transaction
            transaction.commit()
        }
        btChangeFragPrev.setOnClickListener {
            val newFragment = FirstFragment()
            val args = Bundle()
            args.putInt("some",34)
            newFragment.arguments = args

            val transaction = supportFragmentManager.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                replace(R.id.fragment_container, newFragment)
                //addToBackStack(null)
            }

            // Commit the transaction
            transaction.commit()
        }

    }

}
