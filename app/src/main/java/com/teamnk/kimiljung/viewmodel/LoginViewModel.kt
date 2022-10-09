package com.teamnk.kimiljung.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.data.repository.auth.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository()
) : ViewModel() {

    var success: MutableLiveData<Boolean> = MutableLiveData()
    var failure: MutableLiveData<Boolean> = MutableLiveData()

    fun postLogin(loginRequest: LoginRequest) {
        Log.d(TAG, "postLogin: ")
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