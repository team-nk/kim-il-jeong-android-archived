package com.teamnk.kimiljung.feature.register

import com.teamnk.kimiljung.common.BooleanResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegisterAPI {

    @GET("/mail")
    suspend fun verifyEmail(
        @Query("email") email: String,
    ): Response<String>

    @GET("/user/code")
    suspend fun checkVerificationCode(
        @Query("email") email: String,
        @Query("code") verificationCode: String,
    ): Response<BooleanResponse>

    @GET("/user/check")
    suspend fun checkIdDuplication(
        @Query("account-id") accountId: String,
    ): Response<BooleanResponse>

    @POST("/user")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<Void>
}
