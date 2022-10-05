package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.dto.LoginRequest
import com.teamnk.kimiljung.repository.auth.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val rp: LoginRepository
) : ViewModel() {
    private var success: MutableLiveData<Boolean> = MutableLiveData()
    private val failed: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun postLogin(loginRequest: LoginRequest) {
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                rp.login(loginRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }
}