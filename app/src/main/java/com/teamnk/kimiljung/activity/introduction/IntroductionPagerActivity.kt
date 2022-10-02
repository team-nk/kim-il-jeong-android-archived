package com.teamnk.kimiljung.activity.introduction

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.activity.StartActivity
import com.teamnk.kimiljung.adapter.introduction.IntroductionPagerAdapter
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.util.startIntentClearTop

class IntroductionPagerActivity :
    BaseActivity<ActivityIntroductionPagerBinding>(R.layout.activity_introduction_pager) {

    private val sharedPreferences by lazy {
        getSharedPreferences("introductionPage", MODE_PRIVATE)
    }
    private val sharedPreferencesEditor by lazy {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        checkIntroductionPageShown()
        initViewPager()
        initNextLinearLayout()
        initTabLayout()
    }

    private fun checkIntroductionPageShown() {
        if (sharedPreferences.getBoolean("isIntroductionPagerActivityShown", false)) {
            startIntentToMainActivity()
        }
    }

    private fun initViewPager() {
        binding.vpViewPagerViewPager.adapter = IntroductionPagerAdapter(this)
        binding.tabLayoutViewPager.setupWithViewPager(binding.vpViewPagerViewPager)
    }

    private fun initNextLinearLayout() {
        binding.layoutViewPagerNext.setOnClickListener {
            val current = binding.vpViewPagerViewPager.currentItem
            binding.vpViewPagerViewPager.setCurrentItem(current + 1, true)
            if (current == 3) {
                startIntentToMainActivity()
                put("isIntroductionPagerActivityShown", true)
            }
        }
    }

    private fun put(key: String, value: Any?) {
        when (value) {
            is Int -> sharedPreferencesEditor.putInt(key, value).apply()
            is String -> sharedPreferencesEditor.putString(key, value).apply()
            is Boolean -> sharedPreferencesEditor.putBoolean(key, value).apply()
        }
    }

    private fun startIntentToMainActivity() {
        startIntentClearTop(this, StartActivity::class.java)
        finish()
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