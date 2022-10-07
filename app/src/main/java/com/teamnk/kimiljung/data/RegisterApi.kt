package com.teamnk.kimiljung.data

import com.teamnk.kimiljung.dto.DuplicateRequest
import com.teamnk.kimiljung.dto.VerifyCodeRequest
import com.teamnk.kimiljung.dto.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST

// TODO apply interfaces api specs

interface RegisterApi {

    @POST("verify")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest
    )

    @POST("verify/code")
    suspend fun verifyCode(
        @Body verifyCodeRequest: VerifyCodeRequest
    )

    @POST("duplicate")
    suspend fun checkEmailDuplicated(
        @Body duplicateRequest: DuplicateRequest
    )
}
