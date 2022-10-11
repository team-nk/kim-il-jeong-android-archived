package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.startIntentClearTop
import com.teamnk.kimiljung.viewmodel.auth.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    private val viewModel by viewModels<RegisterViewModel>()

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
                startIntentClearTop(this, LoginActivity::class.java)
            }
        }
    }

    override fun observeEvent() {}
}