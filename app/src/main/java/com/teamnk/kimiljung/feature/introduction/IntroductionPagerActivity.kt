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
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_SHOWN
import com.teamnk.kimiljung.util.defaultSharedPreferencesEditor
import com.teamnk.kimiljung.util.showShortToast
import com.teamnk.kimiljung.util.startActivityRemovingBackStack

class IntroductionPagerActivity : BaseActivity<ActivityIntroductionPagerBinding>(
    R.layout.activity_introduction_pager
) {

    private val permissionResult by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it.not()) {
                showShortToast(getString(R.string.activity_introduction_pager_accept_permission))
                startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initNextButton()
        initTabLayout()
        requestPermission()
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
                    moveToStartActivity()
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

    private fun moveToStartActivity() {
        defaultSharedPreferencesEditor.putBoolean(IS_INTRODUCTION_PAGER_SHOWN, true).apply()
        startActivityRemovingBackStack(
            context = this,
            to = StartActivity::class.java,
        )
        finish()
    }

    private fun requestPermission() {
        permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            10,
        )
    }

    override fun observeEvent() {}
}
