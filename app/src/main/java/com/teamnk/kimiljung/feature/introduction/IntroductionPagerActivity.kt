package com.teamnk.kimiljung.feature.introduction

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.teamnk.kimiljung.BuildConfig
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityIntroductionPagerBinding
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_SHOWN
import com.teamnk.kimiljung.util.defaultSharedPreferencesEditor
import com.teamnk.kimiljung.util.showShortToast

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
            with(binding.vpIntroduction) {
                if (currentItem < 3) {
                    setCurrentItem(currentItem + 1, true)
                } else {
                    defaultSharedPreferencesEditor.putBoolean(IS_INTRODUCTION_PAGER_SHOWN, true)
                        .apply()
                    finish()
                }
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

    override fun observeEvent() {}
}
