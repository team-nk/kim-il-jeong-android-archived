package com.teamnk.kimiljung.feature.changepassword

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityChangePasswordBinding
import com.teamnk.kimiljung.feature.login.LoginViewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(
    R.layout.activity_change_password
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this, ChangePasswordViewModelFactory(ChangePasswordRepository())
        )[LoginViewModel::class.java]
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

        }
    }

    private fun initCancelButton() {
        binding.btnActivityChangePasswordCancel.setOnClickListener {
            finish()
        }
    }

    override fun observeEvent() {

    }
}