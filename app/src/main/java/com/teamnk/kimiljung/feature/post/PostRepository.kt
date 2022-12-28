package com.teamnk.kimiljung.feature.post

import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.postAPIProvider
import retrofit2.Response

class PostRepository {
    suspend fun getPostList() : Response<PostListResponse> =
        postAPIProvider.getPostList(
            access_token = accessToken!!
        )
}