package com.teamnk.kimiljung.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import com.teamnk.kimiljung.util.showShortSnackBar
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private var _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                if (it.isSuccessful) {
                    _loginResponse.postValue(it.body())
                } else {
                    println("login error = ${it.code()}")
                }
            }.onFailure {
                println("login failure")
            }
        }
    }
}

// todo 리소스 위치 질문
const val LOGIN_SUCCESS = "Login Success"
const val LOGIN_FAILURE = "Login Failure"