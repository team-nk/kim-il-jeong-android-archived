package com.teamnk.kimiljung.feature.enterbirthday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterBirthdayViewModel(
    private val repository: EnterBirthdayRepository
) : ViewModel() {

    private val _isEnterBirthdaySuccess = MutableLiveData<Boolean>()
    val isEnterBirthdaySuccess: LiveData<Boolean>
        get() = _isEnterBirthdaySuccess

    private var selectedBirthday: String? = String()

    fun setBirthday(birthday: String) {
        selectedBirthday = birthday
    }

    fun enterBirthday() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.enterBirthday(
                    EnterBirthdayRequest(
                        selectedBirthday!!,
                    )
                )
            }.onSuccess {
                _isEnterBirthdaySuccess.postValue(
                    it.isSuccessful
                )
            }
        }
    }
}
