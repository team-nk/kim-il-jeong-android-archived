package com.teamnk.kimiljung.feature.changeuserinformation

import retrofit2.Response

interface ChangeUserInformationAPI {

    suspend fun changeUserInformation(changeUserInformationRequest: ChangeUserInformationRequest): Response<Void>
}