package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.util.showDialogWithSingleButton

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
        binding.btnRegisterVerifyEmail.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            if (email.isNotBlank()) {

            } else {

            }
        }
    }

    private fun initCheckEmailVerifyButton() {
        binding.btnRegisterCheckEmailVerified.setOnClickListener {
            val key = binding.etRegisterVerificationCode.text
            if (key.isNotBlank()) {

            } else {

            }
        }
    }

    private fun initCheckUserIdDuplicate() {
        binding.btnRegisterCheckUserIdDuplicate.setOnClickListener {
            val userId = binding.etRegisterUserId.text.toString()
            if (userId.isNotBlank()) {
                // TODO UserIdDuplicate Logic
                viewModel.run {

                }
            } else {

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
                finish()
            }
        }
    }

    override fun observeEvent() {}
}