package com.teamnk.kimiljung.feature.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val repository: MyPageRepository,
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _selfInformation = MutableLiveData<GetSelfInformationResponse>()
    val selfInformation: LiveData<GetSelfInformationResponse> = _selfInformation

    fun getSelfInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getSelfInformation()
            }.onSuccess {
                if (it.isSuccessful) {
                    _selfInformation.postValue(it.body())
                    Log.d(tag, "getSelfInformation success!")
                } else {
                    Log.d(tag, "getSelfInformation failure..")
                }
            }
        }
    }
}