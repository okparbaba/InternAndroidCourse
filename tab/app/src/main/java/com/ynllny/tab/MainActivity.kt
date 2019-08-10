package com.ynllny.tab

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val simpleFragmentPagerAdapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        //val viewPager: ViewPager = findViewById(R.id.view_pager)
        view_pager.adapter = simpleFragmentPagerAdapter
        //val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(view_pager)
    }
}