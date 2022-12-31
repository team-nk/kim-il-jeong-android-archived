package com.teamnk.kimiljung.feature.postcomment

import retrofit2.Response
import retrofit2.http.*

interface CommentAPI {
    @GET("/comment")
    suspend fun getCommentList(
        @Header("Authorization") access_token : String,
    ) : Response<CommentListResponse>

    @POST("/comment/{post-id}")
    suspend fun postComment(
        @Header("Authorization") access_token: String,
        @Path("post-id") postId : Int,
        @Body commentRequest : CommentRequest,
    ) : Response<Void>
}