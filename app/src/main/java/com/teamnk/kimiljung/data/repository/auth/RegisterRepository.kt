package com.teamnk.kimiljung.repository.auth

import com.teamnk.kimiljung.data.dto.EmailVerificationCodeRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationRequest
import com.teamnk.kimiljung.data.dto.UserIdDuplicationRequest
import com.teamnk.kimiljung.data.registerAPI

class RegisterRepository {
    suspend fun verifyEmail(emailVerificationRequest: EmailVerificationRequest) =
        registerAPI.verifyEmail(emailVerificationRequest)

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: EmailVerificationCodeRequest) =
        registerAPI.checkEmailVerificationCode(emailVerificationCodeRequest)

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: UserIdDuplicationRequest) =
        registerAPI.checkUserIdDuplication(userIdDuplicationRequest)
}
