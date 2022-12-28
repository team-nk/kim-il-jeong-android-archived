package com.teamnk.kimiljung.feature.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(
    private val postRepository : PostRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PostViewModel(
            postRepository = postRepository,
        ) as T
}