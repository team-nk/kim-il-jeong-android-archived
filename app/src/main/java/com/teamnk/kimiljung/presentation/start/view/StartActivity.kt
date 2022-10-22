package com.teamnk.kimiljung.presentation.start.view

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.login.view.LoginActivity
import com.teamnk.kimiljung.util.startIntent

class StartActivity : BaseActivity<ActivityStartBinding>(
    R.layout.activity_start
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStartLoginText()
    }

    private fun initStartLoginText() {
        binding.tvStartLoginWithEmail.setOnClickListener {
            startIntent(this, LoginActivity::class.java)
        }
    }

    override fun observeEvent() {}
}