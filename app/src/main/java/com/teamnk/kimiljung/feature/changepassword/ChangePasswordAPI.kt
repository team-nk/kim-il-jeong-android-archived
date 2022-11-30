package com.teamnk.kimiljung.feature.changepassword

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface ChangePasswordAPI {

    @PATCH("/user/password")
    fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest
    ): Response<Void>
}
