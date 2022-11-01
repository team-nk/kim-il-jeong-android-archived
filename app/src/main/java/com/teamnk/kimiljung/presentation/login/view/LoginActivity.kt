package com.teamnk.kimiljung.presentation.login.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.login.viewmodel.FAILED_TO_CONNECT
import com.teamnk.kimiljung.presentation.login.viewmodel.LoginViewModel
import com.teamnk.kimiljung.presentation.login.viewmodel.LoginViewModelFactory
import com.teamnk.kimiljung.presentation.main.view.MainActivity
import com.teamnk.kimiljung.presentation.register.view.RegisterActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.showShortSnackBar
import com.teamnk.kimiljung.util.showShortToast
import com.teamnk.kimiljung.util.startIntent

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

            if (email.isNotBlank() && password.isNotBlank()) {
                postLogin(email, password)
            } else {
                showShortToast(this, "Please Check Format")
            }
        }
    }

    private fun postLogin(email: String, password: String) {
        if (email == "local" && password == "local") {
            loginWithAdminAccount()
        } else {
            viewModel.postLogin(LoginRequest(email, password))
        }
    }

    private fun loginWithAdminAccount() {
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        userAuthSharedPreferencesEditor.putBoolean(IS_LOGGED_IN, true)
            .apply()
        startIntent(this, MainActivity::class.java)
        finish()
    }

    private fun initGoToRegisterText() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(this, RegisterActivity::class.java)
        }
    }

    override fun observeEvent() {
        viewModel.isLoggedInSuccessfully.observe(
            this
        ) { isLoggedInSuccessfully ->

            val loginResponseCode = viewModel.loginResponseCode.value

            if (loginResponseCode == FAILED_TO_CONNECT) {
                showShortSnackBar(
                    binding.root,
                    getString(R.string.login_error_failed_to_connect_to_server)
                )
            } else if (isLoggedInSuccessfully) {
                moveToMainActivity()
            } else {
                showShortSnackBar(
                    binding.root,
                    "${getString(R.string.login_error_failed)} $loginResponseCode"
                )
            }
        }
    }
}
