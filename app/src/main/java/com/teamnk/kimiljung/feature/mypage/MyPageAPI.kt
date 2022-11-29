package com.teamnk.kimiljung.feature.mypage

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageAPI {

    @GET("/user")
    suspend fun getSelfInformation(
        @Header("Authorization") accessToken: String,
    ): Response<GetSelfInformationResponse>
}