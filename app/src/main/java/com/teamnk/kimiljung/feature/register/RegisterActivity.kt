package com.teamnk.kimiljung.feature.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityRegisterBinding
import com.teamnk.kimiljung.feature.login.LoginActivity
import com.teamnk.kimiljung.util.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RegisterViewModelFactory(
                RegisterRepository(), this.application,
            ),
        )[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWidgets()
    }

    private fun initWidgets() {
        initNextButton()
        initVerifyEmailButton()
        initCheckVerificationCodeButton()
        initCheckIdDuplicationButton()
    }

    private fun initVerifyEmailButton() {
        with(binding) {
            btnActivityRegisterVerifyEmail.setOnClickListener {
                etActivityRegisterEmail.text.toString().run {
                    if (this.isNotBlank()) {
                        viewModel.verifyEmail(this)
                    } else {
                        showShortSnackBar(
                            binding.root,
                            getString(
                                R.string.error_activity_register_please_enter_email,
                            ),
                        )
                    }
                }
            }
        }
    }

    private fun initCheckVerificationCodeButton() {
        with(binding) {
            btnActivityRegisterCheckVerificationCode.setOnClickListener {
                etActivityRegisterVerificationCode.text.toString().run {
                    if (this.isNotBlank()) {
                        viewModel.checkVerificationCode(this)
                    } else {
                        showShortSnackBar(
                            view = binding.root,
                            text = getString(
                                R.string.activity_register_incorrect_verification_code,
                            ),
                        )
                    }
                }
            }
        }

        binding.run {

        }
    }

    private fun initCheckIdDuplicationButton() {
        with(binding) {
            btnActivityRegisterCheckIdDuplication.setOnClickListener {
                etActivityRegisterId.text.toString().run {
                    if (this.isNotBlank()) {
                        viewModel.checkIdDuplication(
                            accountId = this,
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
    }

    private fun initNextButton() {
        binding.btnActivityRegisterNext.setOnClickListener {
            viewModel.register(
                RegisterRequest(
                    email = binding.etActivityRegisterEmail.text.toString(),
                    verificationCode = binding.etActivityRegisterVerificationCode.text.toString(),
                    accountId = binding.etActivityRegisterId.text.toString(),
                    password = binding.etActivityRegisterPassword.text.toString(),
                    repeatPassword = binding.etActivityRegisterPasswordRepeat.text.toString(),
                ),
            )
        }
    }

    // todo cleanup (viewModel)
    override fun observeEvent() {
        viewModel.checkIdDuplicationResponse.observe(
            this,
        ) {
            if (it) {
                showShortSnackBar(
                    binding.root,
                    getString(R.string.activity_register_available_id),
                )
                binding.etActivityRegisterId.disable()
                binding.btnActivityRegisterCheckIdDuplication.disable()

            } else {
                showShortSnackBar(
                    binding.root,
                    getString(R.string.activity_register_unavailbale_id),
                )
            }
        }

        viewModel.isRegisterSuccess.observe(
            this,
        ) {
            if (it) {
                showDialogWithSingleButton(
                    this,
                    getString(R.string.activity_register_dialog_title_register_success),
                    getString(R.string.activity_register_dialog_content_register_success),
                ) {
                    startActivity(
                        this, LoginActivity::class.java,
                    )
                }
            }
        }

        viewModel.shouldShowSnackBar.observe(
            this,
        ) {
            showShortSnackBar(
                binding.root,
                it,
            )
        }

        viewModel.isEmailVerificationCodeSent.observe(
            this,
        ) {
            //TODO remove
            showShortToast("Success")
            if (it) {
                with(binding) {
                    etActivityRegisterEmail.disable()
                    btnActivityRegisterVerifyEmail.disable()
                }
            }
        }

        viewModel.isVerificationCodeChecked.observe(
            this,
        ) {
            // TODO remove
            showShortToast("Success")
            if (it) {
                with(binding) {
                    etActivityRegisterVerificationCode.disable()
                    btnActivityRegisterCheckVerificationCode.disable()
                }
            }
        }
    }
}

