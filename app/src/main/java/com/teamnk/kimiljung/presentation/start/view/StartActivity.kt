package com.teamnk.kimiljung.presentation.start.view

import android.content.Intent
import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.introduction.view.IntroductionPagerActivity
import com.teamnk.kimiljung.presentation.login.view.LoginActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_SHOWN
import com.teamnk.kimiljung.util.startIntent

class StartActivity : BaseActivity<ActivityStartBinding>(
    R.layout.activity_start
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntroductionPageShown()

        initStartLoginText()
    }

    private fun checkIntroductionPageShown() {
        if (!defaultSharedPreferences.getBoolean(
                IS_INTRODUCTION_PAGER_SHOWN,
                false
            )
        ) {
            startActivity(Intent(this, IntroductionPagerActivity::class.java))
        }
    }

    private fun initStartLoginText() {
        binding.tvStartLoginWithEmail.setOnClickListener {
            startIntent(this, LoginActivity::class.java)
        }
    }

    override fun observeEvent() {}
}