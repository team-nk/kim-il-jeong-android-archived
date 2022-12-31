package com.teamnk.kimiljung.feature.postcomment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CommentViewModelFactory(
    private val commentRepository: CommentRepository,
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CommentViewModel(
            commentRepository = commentRepository,
            application = application,
        ) as T
}