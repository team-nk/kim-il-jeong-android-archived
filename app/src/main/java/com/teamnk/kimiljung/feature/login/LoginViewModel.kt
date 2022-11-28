package com.teamnk.kimiljung.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginResponseCode = MutableLiveData<Int>()
    val loginResponseCode: LiveData<Int> = _loginResponseCode

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                _loginResponseCode.postValue(
                    it.code()
                )
            }
        }
    }
}
