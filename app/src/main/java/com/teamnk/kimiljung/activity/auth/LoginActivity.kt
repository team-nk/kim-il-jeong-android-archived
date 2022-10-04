package com.teamnk.kimiljung.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.activity.MainActivity
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentClearTop
import com.teamnk.kimiljung.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel
        initLoginButton()
        initGoToRegisterText()
    }

    private fun initLoginButton() {
        binding.btnLoginLogin.setOnClickListener {
            // Todo Server Logic
            startIntentClearTop(this, MainActivity::class.java)
        }
    }

    private fun initGoToRegisterText() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(this, RegisterActivity::class.java)
        }
    }
}