package com.teamnk.kimiljung.feature.postcomment

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CommentAPI {
    @GET("/comment")
    suspend fun getCommentList(
        @Header("Authorization") access_token : String,
    ) : Response<CommentListResponse>
}