package com.teamnk.kimiljung.feature.mypage

import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.myPageAPIProvider
import retrofit2.Response

class MyPageRepository {
    suspend fun getSelfInformation(): Response<GetSelfInformationResponse> {
        return myPageAPIProvider.getSelfInformation(
            access_token = accessToken!!
        )
    }
}