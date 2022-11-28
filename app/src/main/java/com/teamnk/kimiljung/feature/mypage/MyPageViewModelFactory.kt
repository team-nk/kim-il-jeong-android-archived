package com.teamnk.kimiljung.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MyPageViewModelFactory(
    private val myPageRepository: MyPageRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyPageViewModel(myPageRepository) as T
    }
}