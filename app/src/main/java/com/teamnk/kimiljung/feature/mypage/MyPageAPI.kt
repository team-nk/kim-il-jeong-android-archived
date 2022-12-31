package com.teamnk.kimiljung.feature.mypage

import retrofit2.Response
import retrofit2.http.GET

interface MyPageAPI {

    @GET("/user")
    suspend fun getSelfInformation(): Response<GetSelfInformationResponse>
}