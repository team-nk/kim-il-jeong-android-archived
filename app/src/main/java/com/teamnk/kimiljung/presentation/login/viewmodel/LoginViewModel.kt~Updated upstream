package com.teamnk.kimiljung.presentation.login.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    var success: MutableLiveData<Boolean> = MutableLiveData()
    var failure: MutableLiveData<Boolean> = MutableLiveData()

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                success
            }.onFailure {
                failure
            }.also {
                Log.d(TAG, "$success, $failure")
            }
        }
    }
}