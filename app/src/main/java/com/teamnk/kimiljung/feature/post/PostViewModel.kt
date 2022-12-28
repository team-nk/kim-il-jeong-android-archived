package com.teamnk.kimiljung.feature.post

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.teamnk.kimiljung.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(
    private val postRepository: PostRepository,
    application: Application,
) : AndroidViewModel(application) {

    init {
        getPostList()
    }

    private val AndroidViewModel.context: Context
        get() = getApplication<Application>().applicationContext

    private val _postListResponse = MutableLiveData<Response<PostListResponse>>()
    val postListResponse: LiveData<Response<PostListResponse>> = _postListResponse

    private val _snackBarMessage = MutableLiveData<String>()
    val snackBarMessage: LiveData<String> = _snackBarMessage

    private fun getPostList() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostList()
            }.onSuccess {
                if (it.isSuccessful) {
                    _postListResponse.postValue(it)
                } else {
                    setSnackBarMessage(
                        context.getString(
                            R.string.error_loading_failed
                        )
                    )
                }
            }
        }
    }

    private fun setSnackBarMessage(message: String) {
        _snackBarMessage.postValue(message)
    }
}