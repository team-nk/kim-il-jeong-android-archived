package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN
import com.teamnk.kimiljung.util.SharedPreferencesName.USER_AUTH
import com.teamnk.kimiljung.util.getSharedPreferencesEditor
import com.teamnk.kimiljung.util.initializeSharedPreferences
import com.teamnk.kimiljung.util.putInSharedPreferences
import com.teamnk.kimiljung.util.startIntent

class StartActivity : BaseActivity<ActivityStartBinding>(
    R.layout.activity_start
) {
    private val sharedPreferences by lazy {
        initializeSharedPreferences(this, USER_AUTH, MODE_PRIVATE)
    }

    private val sharedPreferencesEditor by lazy {
        getSharedPreferencesEditor(sharedPreferences)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStartLoginText()
    }

    private fun initStartLoginText() {
        binding.tvStartLoginWithEmail.setOnClickListener {
            startIntent(this, LoginActivity::class.java)

            putInSharedPreferences(
                sharedPreferencesEditor,
                IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN,
                true
            )
        }
    }

    override fun observeEvent() {}
}