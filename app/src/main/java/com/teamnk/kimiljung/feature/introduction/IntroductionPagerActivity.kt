package com.teamnk.kimiljung.feature.introduction

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_SHOWN
import com.teamnk.kimiljung.util.startIntentWithRemovingActivityStack

class IntroductionPagerActivity : BaseActivity<ActivityIntroductionPagerBinding>(
    R.layout.activity_introduction_pager
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
        initNextButton()
        initTabLayout()
    }

    private fun initViewPager() {
        binding.vpIntroduction.adapter = IntroductionPagerAdapter(this)
        binding.tabLayoutIntroductionViewPager.setupWithViewPager(binding.vpIntroduction)
    }

    private fun initNextButton() {
        binding.btnIntroductionNext.setOnClickListener {
            val current = binding.vpIntroduction.currentItem
            binding.vpIntroduction.setCurrentItem(current + 1, true)
            if (current == 3) {
                moveToStartActivity()
            }
        }
    }

    private fun initTabLayout() {
        with(binding.tabLayoutIntroductionViewPager) {
            when (id) {
                0 -> getTabAt(0)?.select()
                1 -> getTabAt(1)?.select()
                2 -> getTabAt(2)?.select()
                3 -> getTabAt(3)?.select()
                4 -> getTabAt(4)?.select()
                else -> {}
            }
        }
    }

    private fun moveToStartActivity() {
        defaultSharedPreferencesEditor.putBoolean(IS_INTRODUCTION_PAGER_SHOWN, true)
            .apply()
        startIntentWithRemovingActivityStack(this, StartActivity::class.java)
        finish()
    }

    override fun observeEvent() {}
}