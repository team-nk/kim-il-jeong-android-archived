package com.teamnk.kimiljung.feature.mypage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val repository: MyPageRepository,
    mApplication: Application,
) : AndroidViewModel(mApplication) {

    private val tag = this.javaClass.simpleName

    private val _selfInformation = MutableLiveData<GetSelfInformationResponse>()
    val selfInformation: LiveData<GetSelfInformationResponse> = _selfInformation

    private val _snackBarMessage = MutableLiveData<String>()
    val snackBarMessage: LiveData<String> = _snackBarMessage

    fun getSelfInformation() {
        viewModelScope.launch(Dispatchers.IO) {
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