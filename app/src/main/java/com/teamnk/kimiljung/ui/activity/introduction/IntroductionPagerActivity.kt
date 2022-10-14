package com.teamnk.kimiljung.ui.activity.introduction

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.ui.activity.auth.StartActivity
import com.teamnk.kimiljung.ui.adapter.introduction.IntroductionPagerAdapter
import com.teamnk.kimiljung.util.SharedPreferencesKey.INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN
import com.teamnk.kimiljung.util.SharedPreferencesName.INTRODUCTION_PAGER_ACTIVITY
import com.teamnk.kimiljung.util.initializeSharedPreferences
import com.teamnk.kimiljung.util.putInSharedPreferences
import com.teamnk.kimiljung.util.startIntentClearTop

class IntroductionPagerActivity : BaseActivity<ActivityIntroductionPagerBinding>(
    R.layout.activity_introduction_pager
) {

    private val sharedPreferences by lazy {
        initializeSharedPreferences(this, INTRODUCTION_PAGER_ACTIVITY, MODE_PRIVATE)
    }
    private val sharedPreferencesEditor by lazy {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIntroductionPageShown()
        initViewPager()
        initNextLinearLayout()
        initTabLayout()
    }

    private fun checkIntroductionPageShown() {
        if (sharedPreferences.getBoolean(INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN, false)) {
            moveToStartActivity()
        }
    }

    private fun initViewPager() {
        binding.vpIntroduction.adapter = IntroductionPagerAdapter(this)
        binding.tabLayoutIntroductionViewPager.setupWithViewPager(binding.vpIntroduction)
    }

    private fun initNextLinearLayout() {
        binding.viewGroupIntroductionNext.setOnClickListener {
            val current = binding.vpIntroduction.currentItem
            binding.vpIntroduction.setCurrentItem(current + 1, true)
            if (current == 3) {
                moveToStartActivity()
                putInSharedPreferences(
                    sharedPreferencesEditor, INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN, true
                )
            }
        }
    }

    private fun moveToStartActivity() {
        startIntentClearTop(this, StartActivity::class.java)
        finish()
    }

    private fun initTabLayout() {
        with(binding.tabLayoutIntroductionViewPager) {
            when (this.id) {
                0 -> getTabAt(0)?.select()
                1 -> getTabAt(1)?.select()
                2 -> getTabAt(2)?.select()
                3 -> getTabAt(3)?.select()
                4 -> getTabAt(4)?.select()
                else -> {}
            }
        }
    }

    override fun observeEvent() {}
}