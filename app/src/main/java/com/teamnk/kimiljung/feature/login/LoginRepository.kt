package com.teamnk.kimiljung.feature.login

import com.teamnk.kimiljung.api.authAPI
import retrofit2.Response

class LoginRepository {

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        authAPI.login(loginRequest)
}
