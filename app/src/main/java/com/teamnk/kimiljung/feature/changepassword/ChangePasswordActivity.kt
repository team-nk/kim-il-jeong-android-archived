package com.teamnk.kimiljung.feature.changepassword

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityChangePasswordBinding
import com.teamnk.kimiljung.util.showShortSnackBar

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(
    R.layout.activity_change_password
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this, ChangePasswordViewModelFactory(
                ChangePasswordRepository()
            )
        )[ChangePasswordViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionButtons()
    }

    private fun initActionButtons() {
        initCancelButton()
        initChangeButton()
    }

    private fun initChangeButton() {
        binding.btnActivityChangePasswordChange.setOnClickListener {
            viewModel.changePassword(
                ChangePasswordRequest(
                    oldPassword = binding.etActivityChangePasswordOldPassword.text.toString(),
                    newPassword = binding.etActivityChangePasswordNewPassword.text.toString(),
                    newPasswordRepeat = binding.etActivityChangePasswordNewPasswordRepeat.text.toString(),
                )
            )
        }
    }

    private fun initCancelButton() {
        binding.btnActivityChangePasswordCancel.setOnClickListener {
            finish()
        }
    }

    override fun observeEvent() {
        viewModel.isPasswordChangeSuccess.observe(
            this
        ) {
            if (it) {
                setResult(
                    RESULT_OK,
                    Intent().putExtra(
                        "isChangePasswordSuccess",
                        true,
                    ),
                ).also {
                    finish()
                }
            } else {
                showShortSnackBar(
                    view = binding.root,
                    text = getString(R.string.error_failed_to_connect_to_server),
                )
            }
        }
    }
}