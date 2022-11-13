package com.teamnk.kimiljung.feature.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {

    private val _registerResponse = MutableLiveData<Response<Void>>()
    val registerResponse: LiveData<Response<Void>> = _registerResponse

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    repository.register(registerRequest)
                }
            }.onSuccess {
                _registerResponse.postValue(it)
            }.onFailure {
                println("Register Failure.. $it")
            }
        }
    }
}