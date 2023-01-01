package com.teamnk.kimiljung.feature.enterbirthday

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterBirthdayViewModel(
    private val repository: EnterBirthdayRepository,
    private val mApplication: Application,
) : AndroidViewModel(mApplication) {

    private val _isEnterBirthdaySuccess = MutableLiveData<Boolean>()
    val isEnterBirthdaySuccess: LiveData<Boolean>
        get() = _isEnterBirthdaySuccess

    private val _snackBarMessage = MutableLiveData<String>()
    val snackBarMessage : LiveData<String>
    get() = _snackBarMessage

    private lateinit var selectedBirthday: String

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
