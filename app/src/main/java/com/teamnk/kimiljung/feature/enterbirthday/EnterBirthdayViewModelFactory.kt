package com.teamnk.kimiljung.feature.enterbirthday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class EnterBirthdayViewModelFactory(private val loginRepository: EnterBirthdayRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EnterBirthdayViewModel(loginRepository) as T
    }
}