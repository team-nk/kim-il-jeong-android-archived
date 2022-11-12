package com.teamnk.kimiljung.api

import com.teamnk.kimiljung.feature.login.LoginRequest
import com.teamnk.kimiljung.feature.login.LoginResponse
import com.teamnk.kimiljung.feature.register.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {


    @POST("/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/user")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Void>
}

val authAPI: AuthAPI = RetrofitClient.getRetrofit()!!.create(AuthAPI::class.java)