package com.teamnk.kimiljung.feature.changepassword

import com.teamnk.kimiljung.api.changePasswordAPIProvider
import retrofit2.Response

class ChangePasswordRepository {

    suspend fun changePassword(
        changePasswordRequest: ChangePasswordRequest
    ): Response<Void> {
        return changePasswordAPIProvider.changePassword(
            changePasswordRequest = changePasswordRequest
        )
    }
}