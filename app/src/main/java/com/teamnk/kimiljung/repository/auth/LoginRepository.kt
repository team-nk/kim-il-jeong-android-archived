package com.teamnk.kimiljung.repository.auth

import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.data.dto.LoginResponse
import com.teamnk.kimiljung.data.loginApi

class LoginRepository(
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse =
        loginApi.login(loginRequest)
}