package com.teamnk.kimiljung.feature.register

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterAPI {

    @GET("/user/check")
    suspend fun checkIdDuplication(
        @Body checkIdDuplicationRequest: CheckIdDuplicationRequest
    ): Response<Boolean>

    @POST("/user")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<Void>
}
