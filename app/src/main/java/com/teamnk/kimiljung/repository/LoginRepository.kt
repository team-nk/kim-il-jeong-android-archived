package com.teamnk.kimiljung.repository

import com.teamnk.kimiljung.data.LoginApi
import com.teamnk.kimiljung.dto.LoginRequest
import com.teamnk.kimiljung.dto.LoginResponse

class LoginRepository(
    private val loginApi: LoginApi
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse =
        loginApi.login(loginRequest)
}