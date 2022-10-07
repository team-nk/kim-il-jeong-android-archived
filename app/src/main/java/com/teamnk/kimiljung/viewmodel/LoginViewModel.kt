package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.repository.auth.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val rp: LoginRepository
) : ViewModel() {
    var success: MutableLiveData<Boolean> = MutableLiveData()
    val failed: MutableLiveData<Boolean> = MutableLiveData()

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                rp.login(loginRequest)
            }.onSuccess {
                success
            }.onFailure {
                failed
            }
        }
    }
}