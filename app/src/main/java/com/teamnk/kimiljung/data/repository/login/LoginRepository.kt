package com.teamnk.kimiljung.data.repository.login

import com.teamnk.kimiljung.data.api.loginAPI
import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import retrofit2.Response

class LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        loginAPI.login(loginRequest)
}
