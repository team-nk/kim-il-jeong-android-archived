package com.teamnk.kimiljung.presentation.login.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    var _success = MutableLiveData<Boolean>()
    var _failure = MutableLiveData<Boolean>()

    val success: LiveData<Boolean>
        get() = _success
    val failure: LiveData<Boolean>
        get() = _failure

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                _success
            }.onFailure {
                _failure
            }.also {
                Log.d(TAG, "$_success, $_failure")
            }
        }
    }
}