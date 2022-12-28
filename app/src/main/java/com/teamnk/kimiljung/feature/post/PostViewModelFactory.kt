package com.teamnk.kimiljung.feature.post

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(
    private val postRepository : PostRepository,
    private val application : Application,
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PostViewModel(
            postRepository = postRepository,
            application = application,
        ) as T
}