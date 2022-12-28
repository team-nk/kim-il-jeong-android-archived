package com.teamnk.kimiljung.feature.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository,
    private val mApplication: Application,
) : AndroidViewModel(mApplication) {

    private val tag = this.javaClass.simpleName

    private val _shouldShowSnackBar = MutableLiveData<Pair<Boolean, String>>()
    internal val shouldShowSnackBar: LiveData<Pair<Boolean, String>>
        get() = _shouldShowSnackBar

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    internal val checkIdDuplicationResponse: LiveData<Boolean> = _checkIdDuplicationResponse

    private val _canRegister = MutableLiveData<Boolean>()
    internal val canRegister: LiveData<Boolean> = _canRegister

    private val _isEmailVerificationCodeSent = MutableLiveData<Boolean>()
    internal val isEmailVerificationCodeSent: LiveData<Boolean> = _isEmailVerificationCodeSent

    private val _isVerificationCodeChecked = MutableLiveData<Boolean>()
    internal val isVerificationCodeChecked: LiveData<Boolean> = _isVerificationCodeChecked

    private val _isIdDuplicationChecked = MutableLiveData<Boolean>()
    internal val isIdDuplicationChecked: LiveData<Boolean> = _isIdDuplicationChecked

    internal fun verifyEmail(
        email: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.verifyEmail(
                    email,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    when (it.code()) {
                        200 -> {
                            _isEmailVerificationCodeSent.postValue(
                                true,
                            )
                        }
                        else -> {
                            _shouldShowSnackBar.postValue(
                                Pair(
                                    true, mApplication.getString(
                                        R.string.error_activity_register_please_check_email_format,
                                    )
                                )
                            )
                        }
                    }
                } else {
                    _shouldShowSnackBar.postValue(
                        Pair(
                            true,
                            mApplication.getString(
                                R.string.error_failed_to_connect_to_server,
                            ),
                        )
                    )
                }
            }.onFailure {
                _shouldShowSnackBar.postValue(
                    Pair(
                        true, mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        )
                    )
                )
            }
        }
    }

    fun checkVerificationCode(
        checkVerificationCodeRequest: CheckVerificationCodeRequest,
    ) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun checkIdDuplication(
        checkIdDuplicationRequest: CheckIdDuplicationRequest,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.checkIdDuplication(
                    checkIdDuplicationRequest
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _checkIdDuplicationResponse.postValue(it.body())
                    _isIdDuplicationChecked.postValue(true)
                }
            }
        }
    }

    fun register(
        registerRequest: RegisterRequest,
    ) {
        if (isEmailVerificationCodeSent.value == true && isVerificationCodeChecked.value == true && isIdDuplicationChecked.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    repository.register(
                        registerRequest,
                    )
                }.onSuccess {
                    if (it.isSuccessful) {
                        _canRegister.postValue(
                            it.isSuccessful
                        )
                    } else {
                        _canRegister.postValue(
                            false
                        )
                    }
                }
            }
        } else {
            _shouldShowSnackBar.postValue(
                Pair(
                    true,
                    mApplication.getString(
                        R.string.error_activity_register_please_enter_necessary_information,
                    ),
                )
            )
        }
    }
}