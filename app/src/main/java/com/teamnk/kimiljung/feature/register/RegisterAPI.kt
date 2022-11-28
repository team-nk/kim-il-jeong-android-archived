package com.teamnk.kimiljung.feature.register

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface RegisterAPI {

    @GET("/user/check")
    suspend fun checkIdDuplication(
        @Body checkIdDuplicationRequest: CheckIdDuplicationRequest
    ): Response<Boolean>
}
