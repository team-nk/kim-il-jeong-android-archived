package com.teamnk.kimiljung.ui.adapter.introduction

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

    private val layoutInflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    private val images = intArrayOf(
        R.drawable.introduction_pager_image_1,
        R.drawable.introduction_pager_image_2,
        R.drawable.introduction_pager_image_3,
        R.drawable.introduction_pager_image_4
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("MissingInflatedId", "InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.item_introduction_pager, null)
        val image = view.findViewById<ImageView>(R.id.img_item_viewpager)
        val viewpager = container as ViewPager

        image.setImageResource(this.images[position])
        viewpager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewpager = container as ViewPager
        val view = `object` as View
        viewpager.removeView(view)
    }
}