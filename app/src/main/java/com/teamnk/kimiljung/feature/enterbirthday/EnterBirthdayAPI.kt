package com.teamnk.kimiljung.feature.enterbirthday

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface EnterBirthdayAPI {

    @PATCH("/user/birthday")
    suspend fun enterBirthday(
        @Body enterBirthdayRequest: EnterBirthdayRequest,
    ): Response<Void>
}