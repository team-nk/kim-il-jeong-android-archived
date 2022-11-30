package com.teamnk.kimiljung.feature.changepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ChangePasswordViewModelFactory(
    private val changePasswordRepository: ChangePasswordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChangePasswordViewModel(changePasswordRepository) as T
    }
}