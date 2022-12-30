package com.teamnk.kimiljung.feature.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository,
    private val mApplication: Application,
) : AndroidViewModel(mApplication) {

    private val tag = this.javaClass.simpleName

    private val _shouldShowSnackBar = MutableLiveData<String>()
    internal val shouldShowSnackBar: LiveData<String>
        get() = _shouldShowSnackBar

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    internal val checkIdDuplicationResponse: LiveData<Boolean> = _checkIdDuplicationResponse

    private val _registerSuccess = MutableLiveData<Boolean>()
    internal val registerSuccess: LiveData<Boolean> = _registerSuccess

    private val _isEmailVerificationCodeSent = MutableLiveData<Boolean>()
    internal val isEmailVerificationCodeSent: LiveData<Boolean> = _isEmailVerificationCodeSent

    private val _isVerificationCodeChecked = MutableLiveData<Boolean>()
    internal val isVerificationCodeChecked: LiveData<Boolean> = _isVerificationCodeChecked

    private val _isIdDuplicationChecked = MutableLiveData<Boolean>()
    internal val isIdDuplicationChecked: LiveData<Boolean> = _isIdDuplicationChecked

    private lateinit var email: String

    internal fun verifyEmail(
        email: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                this@RegisterViewModel.email = email
                repository.verifyEmail(email)
            }.onSuccess {
                if (it.isSuccessful) {
                    when (it.code()) {
                        200 -> {
                            _isEmailVerificationCodeSent.postValue(true)
                        }
                        else -> {
                            _shouldShowSnackBar.postValue(
                                mApplication.getString(
                                    R.string.error_activity_register_please_check_email_format,
                                ),
                            )
                        }
                    }
                } else {
                    _shouldShowSnackBar.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        ),
                    )
                }
            }
        }
    }

    internal fun checkVerificationCode(
        verificationCode: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.checkVerificationCode(
                    this@RegisterViewModel.email,
                    verificationCode,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    when (it.code()) {
                        200 -> {
                            _isVerificationCodeChecked.postValue(true)
                        }
                        else -> {
                            _shouldShowSnackBar.postValue(
                                mApplication.getString(
                                    R.string.activity_register_error_please_enter_correct_verification_code,
                                ),
                            )
                        }
                    }
                } else {
                    _shouldShowSnackBar.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        ),
                    )
                }
            }
        }
    }

    internal fun checkIdDuplication(
        accountId: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.checkIdDuplication(
                    accountId,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    if (it.body()!!.boolean) {
                        _checkIdDuplicationResponse.postValue(it.body()!!.boolean)
                        _isIdDuplicationChecked.postValue(true)
                    } else {
                        _shouldShowSnackBar.postValue(
                            mApplication.getString(
                                R.string.activity_register_error_id_already_exists,
                            ),
                        )
                    }
                }
            }
        }
    }

    internal fun register(
        registerRequest: RegisterRequest,
    ) {
        if (registerRequest.password == registerRequest.repeatPassword) {
            if (isEmailVerificationCodeSent.value == true && isVerificationCodeChecked.value == true && isIdDuplicationChecked.value == true) {
                viewModelScope.launch(Dispatchers.IO) {
                    kotlin.runCatching {
                        repository.register(registerRequest)
                    }.onSuccess {
                        if (it.isSuccessful) {
                            _registerSuccess.postValue(
                                it.isSuccessful
                            )
                        } else {
                            _registerSuccess.postValue(false)
                        }
                    }
                }
            } else {
                _shouldShowSnackBar.postValue(
                    mApplication.getString(
                        R.string.error_activity_register_please_enter_necessary_information,
                    ),
                )
            }
        } else {
            _shouldShowSnackBar.postValue(
                mApplication.getString(R.string.activity_register_error_password_incorrect),
            )
        }
    }
}