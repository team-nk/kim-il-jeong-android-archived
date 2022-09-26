package com.teamnk.kimiljung.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.adapter.IntroductionPagerAdapter
import com.teamnk.kimiljung.databinding.ActivityViewPagerBinding
import com.teamnk.kimiljung.utils.IntentUtil

class IntroductionPagerActivity : AppCompatActivity() {

    private val binding: ActivityViewPagerBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_view_pager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vpViewPagerViewPager.adapter = IntroductionPagerAdapter(this)

        binding.tabLayoutViewPager.setupWithViewPager(binding.vpViewPagerViewPager)

        initNextLinearLayout()

        initTabLayout()
    }

    private fun initNextLinearLayout() {
        binding.linearLayoutViewPager.setOnClickListener {
            val current = binding.vpViewPagerViewPager.currentItem
            binding.vpViewPagerViewPager.setCurrentItem(current + 1, false)
            if (current == 3) {
                IntentUtil.startIntentClearTop(this, StartActivity::class.java)
            }
        }
    }

    private fun initTabLayout() {
        when (binding.tabLayoutViewPager.id) {
            0 -> binding.tabLayoutViewPager.getTabAt(0)?.select()
            1 -> binding.tabLayoutViewPager.getTabAt(1)?.select()
            2 -> binding.tabLayoutViewPager.getTabAt(2)?.select()
            3 -> binding.tabLayoutViewPager.getTabAt(3)?.select()
            4 -> binding.tabLayoutViewPager.getTabAt(4)?.select()
        }
    }
}