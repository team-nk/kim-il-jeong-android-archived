package com.teamnk.kimiljung.feature.post

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PostAPI {

    @GET("/post")
    suspend fun getPostList(
        @Header("Authorization") access_token : String,
    ) : Response<PostListResponse>
}