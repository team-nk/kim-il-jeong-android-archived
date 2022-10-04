package com.teamnk.kimiljung.activity

import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.util.showDialogWithSingleButton
import com.teamnk.kimiljung.util.startIntent

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNextButton()
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
}