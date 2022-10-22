package com.teamnk.kimiljung.data.repository.login

import com.teamnk.kimiljung.data.model.login.LoginRequest
import com.teamnk.kimiljung.data.model.login.LoginResponse
import com.teamnk.kimiljung.data.loginAPI

class LoginRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse =
        loginAPI.login(loginRequest)
}