package com.ynllny.viewpager

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import java.util.ArrayList


class SlidingImage_Adapter(context: Context, private val IMAGES: ArrayList<Int>) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return IMAGES.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.sliding_image_layout, view, false)!!

        val imageView = imageLayout
            .findViewById<View>(R.id.image) as ImageView


        imageView.setImageResource(IMAGES[position])

        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }


}