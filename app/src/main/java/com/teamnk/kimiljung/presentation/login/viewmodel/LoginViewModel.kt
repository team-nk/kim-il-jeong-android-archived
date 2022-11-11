package com.teamnk.kimiljung.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import com.teamnk.kimiljung.data.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Response<LoginResponse>>()
    val loginResponse: LiveData<Response<LoginResponse>> = _loginResponse

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    repository.login(loginRequest)
                }
            }.onSuccess {
                _loginResponse.postValue(it)
            }
        }
    }
}
