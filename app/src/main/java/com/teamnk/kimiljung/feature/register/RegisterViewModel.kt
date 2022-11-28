package com.teamnk.kimiljung.feature.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {

    companion object {
        private const val tag = "RegisterViewModel"
    }

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    val checkIdDuplicationResponse: LiveData<Boolean> = _checkIdDuplicationResponse

    fun checkIdDuplication(
        checkIdDuplicationRequest: CheckIdDuplicationRequest,
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.checkIdDuplication(
                    checkIdDuplicationRequest
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _checkIdDuplicationResponse.postValue(it.body())
                    Log.e(tag, "checkIdDuplication success!")
                } else {
                    Log.e(tag, "checkIdDuplication failure..")
                }
            }
        }
    }
}