package com.teamnk.kimiljung.data.api

import com.teamnk.kimiljung.data.dto.EmailVerificationCodeRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationRequest
import com.teamnk.kimiljung.data.dto.UserIdDuplicationRequest
import retrofit2.http.Body
import retrofit2.http.POST

// TODO apply interfaces api specs

interface RegisterAPI {
    @POST("user")
    suspend fun verifyEmail(
        @Body emailVerificationRequest: EmailVerificationRequest
    )

    @POST("verify/code")
    suspend fun checkEmailVerificationCode(
        @Body emailVerificationCodeRequest: EmailVerificationCodeRequest
    )

    @POST("duplicate")
    suspend fun checkUserIdDuplication(
        @Body userIdDuplicationRequest: UserIdDuplicationRequest
    )
}
