package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.feature.login.LoginActivity
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.showShortSnackBar
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
        initVerifyEmailButton()
        initCheckVerificationCodeButton()
        initCheckIdDuplicationButton()
    }

    private fun initVerifyEmailButton() {
        with(binding) {
            btnActivityRegisterVerifyEmail.setOnClickListener {
                val email = etActivityRegisterEmail.text.toString()
                if (email.isNotBlank()) {
                } else {
                }
            }
        }
    }

    private fun initCheckVerificationCodeButton() {
        with(binding) {
            btnActivityRegisterCheckVerificationCode.setOnClickListener {
                val key = etActivityRegisterVerificationCode.text
                if (key.isNotBlank()) {

                } else {

                }
            }
        }
    }

    private fun initCheckIdDuplicationButton() {
        with(binding) {
            btnActivityRegisterCheckIdDuplication.setOnClickListener {
                val userId = etActivityRegisterId.text.toString()
                if (userId.isNotBlank()) {
                    viewModel.checkIdDuplication(
                        CheckIdDuplicationRequest(
                            accountId = etActivityRegisterId.text.toString()
                        )
                    )
                } else {
                    showShortSnackBar(
                        binding.root,
                        getString(R.string.error_please_enter_id),
                    )
                }
            }
        }
    }

    private fun initNextButton() {
        binding.btnActivityRegisterNext.setOnClickListener {
            showDialogWithSingleButton(
                this,
                getString(R.string.activity_register_dialog_title_register_success),
                getString(R.string.activity_register_dialog_content_register_success)
            ) {
                startActivity(this, LoginActivity::class.java)
            }
        }
    }

    override fun observeEvent() {
        viewModel.checkIdDuplicationResponse.observe(
            this
        ) {
            if (it) {
                showShortSnackBar(
                    binding.root,
                    getString(R.string.activity_register_available_id),
                )
                // TODO 비활성화 로직
            } else {
                showShortSnackBar(
                    binding.root,
                    getString(R.string.activity_register_unavailbale_id),
                )
            }
        }
    }
}