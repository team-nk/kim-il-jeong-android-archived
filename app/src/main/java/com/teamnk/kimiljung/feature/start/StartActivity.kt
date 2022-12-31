package com.teamnk.kimiljung.feature.start

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.feature.introduction.IntroductionPagerActivity
import com.teamnk.kimiljung.feature.login.LoginActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_SHOWN
import com.teamnk.kimiljung.util.startActivity

class StartActivity : BaseActivity<ActivityStartBinding>(
    R.layout.activity_start
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntroductionPageShown()

        initLoginEmailButton()
    }

    private fun checkIntroductionPageShown() {
        if (defaultSharedPreferences.getBoolean(
                IS_INTRODUCTION_PAGER_SHOWN, false
            ).not()
        ) {
            startActivity(
                context = this,
                to = IntroductionPagerActivity::class.java,
            )
        }
    }

    private fun initLoginEmailButton() {
        binding.tvActivityStartLoginEmail.setOnClickListener {
            startActivity(
                context = this,
                to = LoginActivity::class.java,
            )
        }
    }

    override fun observeEvent() {}
}