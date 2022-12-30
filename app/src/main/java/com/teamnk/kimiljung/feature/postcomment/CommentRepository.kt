package com.teamnk.kimiljung.feature.postcomment

import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.commentAPIProvider
import retrofit2.Response

class CommentRepository {
    suspend fun getCommentList(): Response<CommentListResponse> =
        commentAPIProvider.getCommentList(
            access_token = accessToken!!,
        )
}