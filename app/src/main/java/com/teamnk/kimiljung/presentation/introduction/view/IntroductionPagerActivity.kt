package com.teamnk.kimiljung.presentation.introduction.view

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.presentation.start.view.StartActivity
import com.teamnk.kimiljung.presentation.introduction.adapter.IntroductionPagerAdapter
import com.teamnk.kimiljung.util.*

class IntroductionPagerActivity : BaseActivity<ActivityIntroductionPagerBinding>(
    R.layout.activity_introduction_pager
) {

    private val sharedPreferences by lazy {
        initializeSharedPreferences(
            this,
            SharedPreferencesName.INTRODUCTION_PAGER_ACTIVITY,
            MODE_PRIVATE
        )
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
        if (sharedPreferences.getBoolean(SharedPreferencesKey.INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN, false)) {
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
                    sharedPreferencesEditor,
                    SharedPreferencesKey.INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN,
                    true
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