package com.teamnk.kimiljung.feature.register

import com.teamnk.kimiljung.api.registerAPIProvider
import retrofit2.Response

class RegisterRepository {

    suspend fun checkIdDuplication(
        checkIdDuplicationRequest: CheckIdDuplicationRequest,
    ): Response<Boolean> {
        return registerAPIProvider.checkIdDuplication(checkIdDuplicationRequest)
    }

    suspend fun register(
        registerRequest: RegisterRequest
    ): Response<Void> {
        return registerAPIProvider.register(registerRequest)
    }
}