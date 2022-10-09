package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.repository.auth.LoginRepository

class LoginViewModelFactory(private val loginRepository: LoginRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}