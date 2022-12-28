package com.teamnk.kimiljung.feature.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val mApplication: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerRepository, mApplication) as T
    }
}