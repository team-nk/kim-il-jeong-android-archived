package com.teamnk.kimiljung.feature.login

import com.teamnk.kimiljung.feature.login.LoginRequest
import com.teamnk.kimiljung.feature.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginAPI {

    @POST("/user/login")
    @Headers("Content-Type: application/json")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}