package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.feature.login.LoginActivity
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.startActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this, RegisterViewModelFactory(RegisterRepository())
        )[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNextButton()
        initEmailVerifyButton()
        initCheckEmailVerifyButton()
        initCheckUserIdDuplicate()
    }

    private fun initEmailVerifyButton() {
        with(binding) {
            btnRegisterVerifyEmail.setOnClickListener {
                val email = etRegisterEmail.text.toString()
                if (email.isNotBlank()) {

                } else {

                }
            }
        }
    }

    private fun initCheckEmailVerifyButton() {
        with(binding) {
            btnRegisterCheckEmailVerified.setOnClickListener {
                val key = etRegisterVerificationCode.text
                if (key.isNotBlank()) {

                } else {

                }
            }
        }
    }

    private fun initCheckUserIdDuplicate() {
        with(binding) {
            btnRegisterCheckUserIdDuplicate.setOnClickListener {
                val userId = etRegisterUserId.text.toString()
                if (userId.isNotBlank()) {
                    // TODO UserIdDuplicate Logic
                    viewModel.run {

                    }
                } else {

                }
            }
        }
    }

    private fun initNextButton() {
        binding.btnRegisterNext.setOnClickListener {
            showDialogWithSingleButton(
                this,
                getString(R.string.dialog_register_success_title),
                getString(R.string.dialog_register_success_description)
            ) {
                startActivity(this, LoginActivity::class.java)
            }
        }
    }

    override fun observeEvent() {}
}