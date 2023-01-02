package com.teamnk.kimiljung.feature.postcomment

import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.commentAPIProvider
import retrofit2.Response

class CommentRepository {
    suspend fun getCommentList(
        postId : Int,
    ): Response<CommentListResponse> =
        commentAPIProvider.getCommentList(
            access_token = accessToken!!,
            postId = postId,
        )

    suspend fun postComment(
        postId : Int,
        commentRequest : CommentRequest,
    ) : Response<Void> =
        commentAPIProvider.postComment(
            access_token = accessToken!!,
            postId = postId,
            commentRequest = commentRequest,
        )
}