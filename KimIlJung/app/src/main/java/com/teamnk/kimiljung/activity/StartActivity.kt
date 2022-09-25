package com.teamnk.kimiljung.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityStartBinding
import com.teamnk.kimiljung.utils.IntentUtil

class StartActivity : AppCompatActivity() {

    private val binding : ActivityStartBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvStartLoginWithEmail.setOnClickListener {
            IntentUtil.startIntent(this, LoginActivity::class.java)
        }

    }
}