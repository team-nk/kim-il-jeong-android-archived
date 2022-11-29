package com.teamnk.kimiljung.feature.mypage

import com.google.gson.annotations.SerializedName

data class GetSelfInformationResponse(
    @SerializedName("profile") val profileImageURL: String,
    @SerializedName("accountId") val accountId: String,
    @SerializedName("email") val email: String,
)