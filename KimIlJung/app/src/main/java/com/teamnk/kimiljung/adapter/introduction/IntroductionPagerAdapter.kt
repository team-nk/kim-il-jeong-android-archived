package com.teamnk.kimiljung.adapter.introduction

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.teamnk.kimiljung.R

class IntroductionPagerAdapter(private val context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null

    private val image = intArrayOf(
        R.drawable.start_image1,
        R.drawable.start_image2,
        R.drawable.start_image3,
        R.drawable.start_image4
    )

    override fun getCount(): Int {
        return image.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.item_introduction_pager, null)
        val image = view.findViewById<ImageView>(R.id.img_item_viewpager)
        val viewpager = container as ViewPager

        image.setImageResource(this.image[position])
        viewpager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewpager = container as ViewPager
        val view = `object` as View
        viewpager.removeView(view)
    }


}