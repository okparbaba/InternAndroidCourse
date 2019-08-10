package com.ynllny.tab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
class SimpleFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // This determines the fragment for each tab
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            FragmentOne()
        } else {
            FragmentTwo()
        }
    }

    // This determines the number of tabs
    override fun getCount(): Int {
        return 2
    }

    // This determines the title for each tab
    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return when (position) {
            0 -> "1"
            1 -> "2"
            else -> null
        }
    }

}