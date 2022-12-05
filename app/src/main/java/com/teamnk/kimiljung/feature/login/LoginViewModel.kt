package com.teamnk.kimiljung.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginResponseCode = MutableLiveData<Pair<LoginResponse?, Int?>>()
    val loginResponseCode: LiveData<Pair<LoginResponse?, Int?>> = _loginResponseCode

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                repository.login(loginRequest)
            }.onSuccess {
                _loginResponseCode.postValue(
                    Pair(
                        it.body(),
                        it.code(),
                    )
                )
            }
        }
    }
}
