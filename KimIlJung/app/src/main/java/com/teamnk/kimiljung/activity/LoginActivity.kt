package com.teamnk.kimiljung.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentClearTop

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy{
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoginButton()

        initRegisterText()
    }

    private fun initLoginButton() {
        binding.btnLoginLogin.setOnClickListener {
            // Todo Server Logic
            startIntentClearTop(baseContext, MainActivity::class.java)
        }
    }

    private fun initRegisterText() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(baseContext, RegisterActivity::class.java)
        }
    }
}