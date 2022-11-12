package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.showShortSnackBar

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

        initWidgets()
    }

    private fun initWidgets() {
        initVerifyEmailButton()
        initCheckVerificationCodeButton()
        initCheckIdDuplicationButton()
        initNextButton()
    }

    private fun initNextButton() {
        binding.btnRegisterNext.setOnClickListener {
            viewModel.register(
                RegisterRequest(
                    binding.etRegisterUserId.text.toString(),
                    binding.etRegisterVerificationCode.text.toString().toInt(),
                    binding.etRegisterEmail.text.toString(),
                    binding.etRegisterPassword.text.toString(),
                    binding.etRegisterRepeatPassword.text.toString(),
                )
            )
        }
    }

    private fun initCheckIdDuplicationButton() {
        // TODO 아이디 중복 확인 버튼
    }

    private fun initCheckVerificationCodeButton() {
        // TODO 인증번호 확인하기 버튼
    }

    private fun initVerifyEmailButton() {
        // TODO 이메일 인증
    }

    override fun observeEvent() {
        viewModel.registerResponse.observe(
            this,
        ) {
            if (it.isSuccessful) {
                showDialogWithSingleButton(
                    this,
                    getString(R.string.dialog_register_success_title),
                    getString(R.string.dialog_register_success_description),
                ) {
                    finish()
                }
            } else {
                showShortSnackBar(
                    binding.root, "${getString(R.string.register_error_failed)} ${it.code()}"
                )
            }
        }
    }
}