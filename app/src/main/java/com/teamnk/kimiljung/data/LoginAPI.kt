package com.teamnk.kimiljung.data

import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.data.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : LoginResponse
}