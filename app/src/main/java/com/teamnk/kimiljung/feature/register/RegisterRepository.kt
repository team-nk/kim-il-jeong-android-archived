package com.teamnk.kimiljung.feature.register

import com.teamnk.kimiljung.api.registerAPIProvider
import retrofit2.Response

class RegisterRepository : RegisterAPI {

    override suspend fun verifyEmail(verifyEmailRequest: VerifyEmailRequest): Response<Boolean> {
        return registerAPIProvider.verifyEmail(verifyEmailRequest)
    }

    override suspend fun checkIdDuplication(
        checkIdDuplicationRequest: CheckIdDuplicationRequest,
    ): Response<Boolean> {
        return registerAPIProvider.checkIdDuplication(checkIdDuplicationRequest)
    }

    override suspend fun register(
        registerRequest: RegisterRequest
    ): Response<Void> {
        return registerAPIProvider.register(registerRequest)
    }
}