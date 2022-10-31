package com.teamnk.kimiljung.presentation.login.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.login.viewmodel.LoginViewModel
import com.teamnk.kimiljung.presentation.login.viewmodel.LoginViewModelFactory
import com.teamnk.kimiljung.presentation.main.view.MainActivity
import com.teamnk.kimiljung.presentation.register.view.RegisterActivity
import com.teamnk.kimiljung.util.showShortToast
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentWithRemovingActivityStack

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this, LoginViewModelFactory(LoginRepository())
        )[LoginViewModel::class.java]
    }

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

            // TODO 로그인 함수 구현, local 계정 필터링
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
                    startIntentWithRemovingActivityStack(baseContext, MainActivity::class.java)
                }
            }
            success.observe(this@LoginActivity) {
                it.run {
                    // TODO remove toast
                    showShortToast(baseContext, "로그인 실패")
                }
            }
        }
    }
}
