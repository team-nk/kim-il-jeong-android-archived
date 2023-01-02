package com.teamnk.kimiljung.feature.changepassword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val repository: ChangePasswordRepository
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _isPasswordChangeSuccess = MutableLiveData<Boolean>()
    val isPasswordChangeSuccess: LiveData<Boolean> = _isPasswordChangeSuccess

    fun changePassword(
        changePasswordRequest: ChangePasswordRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.changePassword(
                    changePasswordRequest = changePasswordRequest
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _isPasswordChangeSuccess.postValue(true)
                    Log.d(tag, "changePassword success!")
                } else {
                    _isPasswordChangeSuccess.postValue(false)
                    Log.d(tag, "changePassword failure..")
                }
            }
        }
    }
}