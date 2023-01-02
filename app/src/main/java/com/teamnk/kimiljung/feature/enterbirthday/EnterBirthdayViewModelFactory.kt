package com.teamnk.kimiljung.feature.enterbirthday

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class EnterBirthdayViewModelFactory(
    private val loginRepository: EnterBirthdayRepository, private val mApplication: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EnterBirthdayViewModel(loginRepository, mApplication) as T
    }
}