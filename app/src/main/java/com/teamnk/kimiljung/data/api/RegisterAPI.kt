package com.teamnk.kimiljung.data.api

import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationCodeRequest
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationRequest
import com.teamnk.kimiljung.data.model.register.RegisterUserIdDuplicationRequest
import retrofit2.http.Body
import retrofit2.http.POST

// TODO apply interfaces api specs

interface RegisterAPI {
    @POST("user")
    suspend fun verifyEmail(
        @Body emailVerificationRequest: RegisterEmailVerificationRequest
    )

    @POST("verify/code")
    suspend fun checkEmailVerificationCode(
        @Body emailVerificationCodeRequest: RegisterEmailVerificationCodeRequest
    )

    @POST("duplicate")
    suspend fun checkUserIdDuplication(
        @Body userIdDuplicationRequest: RegisterUserIdDuplicationRequest
    )
}
