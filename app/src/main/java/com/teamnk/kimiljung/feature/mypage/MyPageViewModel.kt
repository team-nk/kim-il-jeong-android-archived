package com.teamnk.kimiljung.feature.mypage

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnk.kimiljung.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPageViewModel(
    private val repository: MyPageRepository,
    application: Application,
) : AndroidViewModel(application) {

    private val AndroidViewModel.context: Context
        get() = getApplication<Application>().applicationContext

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
                    setToShowSnackBar(
                        context.getString(
                            R.string.error_loading_failed,
                        )
                    )
                    Log.d(tag, "getSelfInformation failure..")
                }
            }
        }
    }

    private fun setToShowSnackBar(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _snackBarMessage.value = message
        }
    }
}