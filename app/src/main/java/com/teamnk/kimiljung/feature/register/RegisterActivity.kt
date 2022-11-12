package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding

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
        // TODO 다음 버튼
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

    override fun observeEvent() {}
}