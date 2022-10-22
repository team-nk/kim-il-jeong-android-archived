package com.teamnk.kimiljung.data.repository.register

import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationCodeRequest
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationRequest
import com.teamnk.kimiljung.data.model.register.RegisterUserIdDuplicationRequest
import com.teamnk.kimiljung.data.api.registerAPI

class RegisterRepository {
    suspend fun verifyEmail(emailVerificationRequest: RegisterEmailVerificationRequest) =
        registerAPI.verifyEmail(emailVerificationRequest)

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: RegisterEmailVerificationCodeRequest) =
        registerAPI.checkEmailVerificationCode(emailVerificationCodeRequest)

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: RegisterUserIdDuplicationRequest) =
        registerAPI.checkUserIdDuplication(userIdDuplicationRequest)
}
