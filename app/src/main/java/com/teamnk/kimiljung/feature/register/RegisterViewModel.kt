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

    private val _registerResponse = MutableLiveData<Boolean>()
    val registerResponse: LiveData<Boolean> = _registerResponse

    var isEmailVerified = false

    var isVerificationCodeChecked = false

    var isIdDuplicationChecked = false

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
                    isIdDuplicationChecked = true
                    Log.d(tag, "checkIdDuplication success!")
                } else {
                    Log.e(tag, "checkIdDuplication failure..")
                }
            }
        }
    }

    fun register(
        registerRequest: RegisterRequest
    ) {
        if (isEmailVerified && isVerificationCodeChecked && isIdDuplicationChecked) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    repository.register(
                        registerRequest
                    )
                }.onSuccess {
                    if (it.isSuccessful) {
                        _registerResponse.postValue(
                            it.isSuccessful
                        )
                        Log.d(tag, "register success!")
                    } else {
                        _registerResponse.postValue(
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