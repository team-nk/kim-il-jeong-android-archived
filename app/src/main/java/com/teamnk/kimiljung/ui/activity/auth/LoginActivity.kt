package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.ui.activity.MainActivity
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentClearTop
import com.teamnk.kimiljung.viewmodel.auth.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        initLoginButton()
        initGoToRegisterText()
    }

    private fun initBinding() {
        binding.viewModel = viewModel
    }

    private fun initLoginButton() {
        binding.btnLoginLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                if (email == "admin" && password == "admin") {
                    loginWithAdminAccount()
                }
                val loginRequest = LoginRequest(email, password)
                viewModel.postLogin(loginRequest)
                // TODO remove test toast and implement intent to MainActivity
                Toast.makeText(this, "viewModel working", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginWithAdminAccount() {
        goToMainActivity()
    }

    private fun goToMainActivity() {
        startIntent(this, MainActivity::class.java)
        finish()
    }

    private fun initGoToRegisterText() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(this, RegisterActivity::class.java)
        }
    }

    override fun observeEvent() {
        viewModel.run {
            success.observe(this@LoginActivity) {
                it.run {
                    Toast.makeText(baseContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    startIntentClearTop(baseContext, MainActivity::class.java)
                }
            }
            failure.observe(this@LoginActivity) {
                it.run {
                    // TODO toast
                    Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
