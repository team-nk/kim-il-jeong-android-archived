package com.teamnk.kimiljung.feature.register

import com.teamnk.kimiljung.api.registerAPIProvider
import com.teamnk.kimiljung.common.BooleanResponse
import retrofit2.Response

class RegisterRepository : RegisterAPI {

    override suspend fun verifyEmail(email: String): Response<String> {
        return registerAPIProvider.verifyEmail(email)
    }

    override suspend fun checkVerificationCode(checkVerificationCodeRequest: CheckVerificationCodeRequest): Response<BooleanResponse> {
        return registerAPIProvider.checkVerificationCode(checkVerificationCodeRequest)
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