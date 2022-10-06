package com.teamnk.kimiljung.repository.auth

import com.teamnk.kimiljung.data.RegisterApi
import com.teamnk.kimiljung.dto.VerifyRequest

class RegisterRepository(
    private val registerApi: RegisterApi
) {
    suspend fun verify(verifyRequest: VerifyRequest) =
        registerApi.verify(verifyRequest)
}