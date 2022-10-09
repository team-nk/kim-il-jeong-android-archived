package com.teamnk.kimiljung.ui.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.startIntent
import com.teamnk.kimiljung.viewmodel.LoginViewModel
import com.teamnk.kimiljung.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {

    // TODO MOVE
    private val viewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
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
                // TODO Verify Logic
                viewModel.run {

                }
            } else {

            }
        }
    }

    private fun initCheckEmailVerifyButton() {
        binding.btnRegisterCheckEmailVerified.setOnClickListener {
            val key = binding.etRegisterVerificationCode.text
            if (key.isNotBlank()) {
                // TODO Verify Code Logic
                viewModel.run {

                }
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
            ) { startIntent(this, LoginActivity::class.java) }
        }
    }

    override fun observeEvent() {}
}