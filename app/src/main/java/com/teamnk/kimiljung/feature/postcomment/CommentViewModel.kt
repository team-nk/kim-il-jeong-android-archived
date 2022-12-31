package com.teamnk.kimiljung.feature.postcomment

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.teamnk.kimiljung.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CommentViewModel(
    private val commentRepository: CommentRepository,
    application: Application,
) : AndroidViewModel(application) {

    init {
        getCommentList()
    }

    private val AndroidViewModel.context: Context
        get() = getApplication<Application>().applicationContext

    private val _commentListResponse = MutableLiveData<Response<CommentListResponse>>()
    val commentListResponse: LiveData<Response<CommentListResponse>> = _commentListResponse

    private val _snackBarMessage = MutableLiveData<String>()
    val snackBarMessage: LiveData<String> = _snackBarMessage

    private fun getCommentList() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                commentRepository.getCommentList()
            }.onSuccess {
                if (it.isSuccessful) {
                    _commentListResponse.postValue(it)
                } else {
                    setToShowSnackBar(
                        context.getString(R.string.error_loading_failed)
                    )
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