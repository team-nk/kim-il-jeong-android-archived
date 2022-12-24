package com.teamnk.kimiljung.feature.mypage

import com.google.gson.annotations.SerializedName

data class GetSelfInformationResponse(
    @SerializedName("profile") val profileImageURL: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("email") val email: String,
)