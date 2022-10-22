package com.teamnk.kimiljung.data.api

import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}