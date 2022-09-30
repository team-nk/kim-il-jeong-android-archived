package com.teamnk.kimiljung.data

import com.teamnk.kimiljung.dto.LoginRequest
import com.teamnk.kimiljung.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : LoginResponse
}