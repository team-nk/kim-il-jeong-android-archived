package com.teamnk.kimiljung.data

import com.teamnk.kimiljung.data.dto.DuplicateRequest
import com.teamnk.kimiljung.data.dto.VerifyCodeRequest
import com.teamnk.kimiljung.data.dto.VerifyRequest
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
    suspend fun checkIdDuplicated(
        @Body duplicateRequest: DuplicateRequest
    )
}
