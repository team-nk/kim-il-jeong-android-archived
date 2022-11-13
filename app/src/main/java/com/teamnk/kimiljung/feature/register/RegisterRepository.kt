package com.teamnk.kimiljung.feature.register

import com.teamnk.kimiljung.api.authAPI
import retrofit2.Response

class RegisterRepository {

    suspend fun register(registerRequest: RegisterRequest): Response<Void> =
        authAPI.register(registerRequest)
}
