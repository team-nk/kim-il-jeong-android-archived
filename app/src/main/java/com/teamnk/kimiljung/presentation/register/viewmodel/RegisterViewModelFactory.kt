package com.teamnk.kimiljung.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.data.repository.register.RegisterRepository

class RegisterViewModelFactory(private val registerRepository: RegisterRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerRepository) as T
    }
}