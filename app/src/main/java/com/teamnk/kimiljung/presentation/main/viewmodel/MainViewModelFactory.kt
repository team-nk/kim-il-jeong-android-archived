package com.teamnk.kimiljung.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.data.repository.main.MainRepository

class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}
