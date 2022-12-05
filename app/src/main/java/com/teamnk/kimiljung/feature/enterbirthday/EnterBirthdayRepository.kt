package com.teamnk.kimiljung.feature.enterbirthday

import com.teamnk.kimiljung.api.enterBirthdayAPIProvider
import retrofit2.Response

class EnterBirthdayRepository {

    suspend fun enterBirthday(
        enterBirthdayRequest: EnterBirthdayRequest
    ): Response<Void> {
        return enterBirthdayAPIProvider.enterBirthday(
            enterBirthdayRequest,
        )
    }
}