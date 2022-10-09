package com.teamnk.kimiljung.repository.auth
import com.teamnk.kimiljung.data.RegisterAPI
import com.teamnk.kimiljung.data.dto.UserIdDuplicationRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationCodeRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationRequest
// TODO refactor
class RegisterRepository(
    private val registerApi: RegisterAPI
) {
    suspend fun verifyEmail(emailVerificationRequest: EmailVerificationRequest) =
        registerApi.verifyEmail(emailVerificationRequest)

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: EmailVerificationCodeRequest) =
        registerApi.checkEmailVerificationCode(emailVerificationCodeRequest)

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: UserIdDuplicationRequest) =
        registerApi.checkUserIdDuplication(userIdDuplicationRequest)
}
