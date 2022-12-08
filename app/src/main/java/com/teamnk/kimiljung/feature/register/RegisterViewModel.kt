package com.teamnk.kimiljung.feature.register

import android.app.Application
import android.util.Log
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

    private val _shouldShowSnackBar = MutableLiveData<Pair<Boolean, String>>()
    val shouldShowSnackBar: LiveData<Pair<Boolean, String>>
        get() = _shouldShowSnackBar

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    val checkIdDuplicationResponse: LiveData<Boolean> = _checkIdDuplicationResponse

    private val _canRegister = MutableLiveData<Boolean>()
    val canRegister: LiveData<Boolean> = _canRegister

    private val _isEmailVerificationCodeSent = MutableLiveData<Boolean>()
    val isEmailVerificationCodeSent: LiveData<Boolean> = _isEmailVerificationCodeSent

    private val _isVerificationCodeChecked = MutableLiveData<Boolean>()
    val isVerificationCodeChecked: LiveData<Boolean> = _isVerificationCodeChecked

    private val _isIdDuplicationChecked = MutableLiveData<Boolean>()
    val isIdDuplicationChecked: LiveData<Boolean> = _isIdDuplicationChecked

    fun verifyEmail(
        verifyEmailRequest: VerifyEmailRequest,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.verifyEmail(
                    verifyEmailRequest,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _isEmailVerificationCodeSent.postValue(
                        true,
                    )
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
            }
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
                    Log.d(tag, "checkIdDuplication success!")
                } else {
                    Log.e(tag, "checkIdDuplication failure..")
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
                        registerRequest
                    )
                }.onSuccess {
                    if (it.isSuccessful) {
                        _canRegister.postValue(
                            it.isSuccessful
                        )
                        Log.d(tag, "register success!")
                    } else {
                        _canRegister.postValue(
                            false
                        )
                        Log.e(tag, "register failure..")
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