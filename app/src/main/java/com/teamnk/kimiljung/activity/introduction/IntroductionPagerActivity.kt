package com.teamnk.kimiljung.activity.introduction

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.activity.StartActivity
import com.teamnk.kimiljung.adapter.introduction.IntroductionPagerAdapter
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.util.*
import com.teamnk.kimiljung.util.SharedPreferencesKeys.IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN
import com.teamnk.kimiljung.util.SharedPreferencesNames.*

class IntroductionPagerActivity :
    BaseActivity<ActivityIntroductionPagerBinding>(R.layout.activity_introduction_pager) {

    private val sharedPreferences by lazy {
        initializeSharedPreferences(this, INTRODUCTION_PAGE.preferencesName, MODE_PRIVATE)
    }
    private val sharedPreferencesEditor by lazy {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding(this, binding, this)

        checkIntroductionPageShown()
        initViewPager()
        initNextLinearLayout()
        initTabLayout()
    }

    private fun checkIntroductionPageShown() {
        if (sharedPreferences.getBoolean(IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN.key, false)) {
            moveToStartActivity()
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
                moveToStartActivity()
                putInSharedPreferences(sharedPreferencesEditor, IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN.key, true)
            }
        }
    }

    private fun moveToStartActivity() {
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