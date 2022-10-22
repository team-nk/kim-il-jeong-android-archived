package com.teamnk.kimiljung.presentation.login.view

import android.os.Bundle
import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.presentation.main.fragment.base.BaseActivity
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.presentation.login.viewmodel.LoginViewModel
import com.teamnk.kimiljung.presentation.main.view.MainActivity
import com.teamnk.kimiljung.presentation.register.view.RegisterActivity
import com.teamnk.kimiljung.util.showShortToast
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentClearTop

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

            // TODO 로그인 정보 저장
            if (email.isNotBlank() && password.isNotBlank()) {
                if (email == "local" && password == "local") {
                    loginWithAdminAccount()
                }
                val loginRequest = LoginRequest(email, password)
                viewModel.postLogin(loginRequest)
                // TODO remove test toast and implement intent to MainActivity
                showShortToast(this, "viewModel working")
            } else {
                showShortToast(this, "failed")
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
                    showShortToast(baseContext, "로그인 성공!")
                    startIntentClearTop(baseContext, MainActivity::class.java)
                }
            }
            failure.observe(this@LoginActivity) {
                it.run {
                    // TODO remove toast
                    showShortToast(baseContext, "로그인 실패")
                }
            }
        }
    }
}
