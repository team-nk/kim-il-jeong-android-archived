package com.teamnk.kimiljung.data.api

import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
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