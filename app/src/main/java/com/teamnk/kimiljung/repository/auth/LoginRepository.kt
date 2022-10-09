package com.teamnk.kimiljung.repository.auth

import com.teamnk.kimiljung.data.dto.LoginRequest
import com.teamnk.kimiljung.data.dto.LoginResponse
import com.teamnk.kimiljung.data.loginAPI

class LoginRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse =
        loginAPI.login(loginRequest)
}