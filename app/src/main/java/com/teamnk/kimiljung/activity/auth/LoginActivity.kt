package com.teamnk.kimiljung.activity.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.dto.LoginRequest
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoginButton()
        initGoToRegister()
    }

    private fun initLoginButton() {
        binding.btnLoginLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (email.isNotBlank() && password.isNotEmpty()) {
                // TODO Login
                viewModel.run {
                }
            } else {
                // TODO Format Error snackBar
            }
        }
    }

    private fun initGoToRegister() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(this, RegisterActivity::class.java)
        }
    }
}