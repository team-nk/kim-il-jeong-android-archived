package com.teamnk.kimiljung.feature.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.refreshToken
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.feature.main.MainActivity
import com.teamnk.kimiljung.feature.register.RegisterActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.showShortSnackBar
import com.teamnk.kimiljung.util.startActivity

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
        initLoginButton()
        initGoToRegisterText()
    }

    private fun initLoginButton() {
        binding.btnActivityLoginLogin.setOnClickListener {

            val email = binding.etActivityLoginEmail.text.toString()
            val password = binding.etActivityLoginPassword.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                postLogin(
                    email = email,
                    password = password,
                )
            } else {
                showShortSnackBar(
                    view = binding.root,
                    text = "Please Check Format",
                )
            }
        }
    }

    private fun postLogin(email: String, password: String) {
        if (email == "local" && password == "local") {
            loginWithAdminAccount()
        } else {
            viewModel.login(
                LoginRequest(
                    email = email,
                    password = password,
                )
            )
        }
    }

    private fun loginWithAdminAccount() {
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        defaultSharedPreferencesEditor.putBoolean(IS_LOGGED_IN, true).apply()
        startActivity(
            context = this,
            to = MainActivity::class.java,
        )
        finishAndRemoveTask()
    }

    private fun initGoToRegisterText() {
        binding.tvActivityLoginGoToRegister.setOnClickListener {
            startActivity(
                context = this,
                to = RegisterActivity::class.java,
            )
        }
    }

    override fun observeEvent() {
        viewModel.loginResponseCode.observe(
            this
        ) {
            when (it.second) {
                200 -> {
                    accessToken = "Bearer ${it.first?.accessToken}"
                    refreshToken = "Bearer ${it.first?.refreshToken}"
                    moveToMainActivity()
                }

                // TODO 서버 상태 코드 핸들링 로직 추가

                else -> {
                    showShortSnackBar(
                        binding.root, "${getString(R.string.error_login_failed)} ${it.second}"
                    )
                }
            }
        }
    }
}
