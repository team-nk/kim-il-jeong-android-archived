package com.teamnk.kimiljung.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.util.initializeBinding
import com.teamnk.kimiljung.util.startIntent

class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStartLoginText()
    }

    private fun initStartLoginText() {
        binding.tvStartLoginWithEmail.setOnClickListener {
            startIntent(this, LoginActivity::class.java)
        }
    }
}