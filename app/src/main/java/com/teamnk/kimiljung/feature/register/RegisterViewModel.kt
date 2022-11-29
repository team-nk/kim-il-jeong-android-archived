package com.teamnk.kimiljung.feature.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {

    private val tag = "RegisterViewModel"

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    val checkIdDuplicationResponse: LiveData<Boolean> = _checkIdDuplicationResponse

    private val _registerResponse = MutableLiveData<Boolean>()
    val registerResponse: LiveData<Boolean> = _registerResponse

    fun checkIdDuplication(
        checkIdDuplicationRequest: CheckIdDuplicationRequest,
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.checkIdDuplication(
                    checkIdDuplicationRequest
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _checkIdDuplicationResponse.postValue(it.body())
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
        viewModelScope.launch {
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
    }
}