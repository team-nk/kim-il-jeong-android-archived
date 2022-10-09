package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.databinding.ActivityLoginBinding
import com.teamnk.kimiljung.repository.auth.LoginRepository
import com.teamnk.kimiljung.ui.activity.MainActivity
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.util.startIntentClearTop
import com.teamnk.kimiljung.viewmodel.LoginViewModel
import com.teamnk.kimiljung.viewmodel.LoginViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initLoginButton()
        initGoToRegisterText()
    }

    private fun initLoginButton() {
        binding.btnLoginLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (email == "" || password == "") {
                // TODO toast
            } else {
                val loginRequest = LoginRequest(email, password)
                viewModel.postLogin(loginRequest)
                Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initGoToRegisterText() {
        binding.tvLoginGoToRegister.setOnClickListener {
            startIntent(this, RegisterActivity::class.java)
        }
    }

    override fun observeEvent() {
        /*viewModel.run {
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
        }*/
    }
}
