package com.teamnk.kimiljung.feature.mypage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.R
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val repository: MyPageRepository,
    application: Application,
) : AndroidViewModel(application) {

    init {
        getSelfInformation()
    }

    private val tag = this.javaClass.simpleName

    private val _selfInformation = MutableLiveData<GetSelfInformationResponse>()
    val selfInformation: LiveData<GetSelfInformationResponse> = _selfInformation

    private val _shouldShowSnackBar = MutableLiveData<Pair<Boolean, String>>()
    val shouldShowSnackBar: LiveData<Pair<Boolean, String>> = _shouldShowSnackBar

    private fun getSelfInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getSelfInformation()
            }.onSuccess {
                if (it.isSuccessful) {
                    _selfInformation.postValue(it.body())
                    Log.d(tag, "getSelfInformation success!")
                } else {
                    _shouldShowSnackBar.postValue(
                        Pair(true, .getString(R.string.error_loading_failed))
                    )
                    Log.d(tag, "getSelfInformation failure..")
                }
            }
        }
    }
}