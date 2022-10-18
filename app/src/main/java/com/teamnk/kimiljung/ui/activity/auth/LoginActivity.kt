package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.ui.activity.MainActivity
import com.teamnk.kimiljung.util.*
import com.teamnk.kimiljung.util.SharedPreferencesName.USER_AUTH
import com.teamnk.kimiljung.viewmodel.auth.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {
    private val viewModel by viewModels<LoginViewModel>()

    private val sharedPreferences by lazy {
        initializeSharedPreferences(this, USER_AUTH, MODE_PRIVATE)
    }

    private val sharedPreferencesEditor by lazy {
        getSharedPreferencesEditor(sharedPreferences)
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

            // TODO 로그인 정보 저장
            if (email.isNotBlank() && password.isNotBlank()) {
                if (email == "local" && password == "local") {
                    loginWithAdminAccount()
                }
                val loginRequest = LoginRequest(email, password)
                viewModel.postLogin(loginRequest)
                // TODO remove test toast and implement intent to MainActivity
                showShortToast(this, "viewModel working")

                putInSharedPreferences(
                    sharedPreferencesEditor,
                    SharedPreferencesKey.INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN,
                    true
                )
            } else {
                showShortToast(this, "failed")
            }
        }
    }

    private fun loginWithAdminAccount() {
        goToMainActivity()
    }

    private fun goToMainActivity() {
        startIntentClearTop(this, MainActivity::class.java)
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
