package com.teamnk.kimiljung.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private var _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private var _loginResponseCode = MutableLiveData<Int>()
    val loginResponseCode: LiveData<Int> = _loginResponseCode

    private var _isLoggedInSuccessfully = MutableLiveData<Boolean>()
    val isLoggedInSuccessfully: LiveData<Boolean> = _isLoggedInSuccessfully

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                if (it.isSuccessful) {
                    
                    _loginResponse.postValue(it.body())

                    _isLoggedInSuccessfully.postValue(true)
                } else {
                    _loginResponseCode.postValue(it.code())

                    _isLoggedInSuccessfully.postValue(false)
                }
            }.onFailure {
                _loginResponseCode.postValue(FAILED_TO_CONNECT)

                _isLoggedInSuccessfully.postValue(false)
            }
        }
    }
}

// todo 리소스 위치 질문
const val FAILED_TO_CONNECT = -1