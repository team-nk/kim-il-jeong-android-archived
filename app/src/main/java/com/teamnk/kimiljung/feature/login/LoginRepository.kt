package com.teamnk.kimiljung.feature.login

import com.teamnk.kimiljung.api.loginAPI
import retrofit2.Response

class LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        loginAPI.login(loginRequest)
}
