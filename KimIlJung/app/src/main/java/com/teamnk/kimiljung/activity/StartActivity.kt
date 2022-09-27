package com.teamnk.kimiljung.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.util.startIntent

class StartActivity : AppCompatActivity() {

    private val binding: ActivityStartBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStartLoginText()
    }

    private fun initStartLoginText() {
        binding.tvStartLoginWithEmail.setOnClickListener {
            startIntent(baseContext, LoginActivity::class.java)
        }
    }
}