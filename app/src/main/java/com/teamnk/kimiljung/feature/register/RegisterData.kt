package com.teamnk.kimiljung.feature.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("accountId") val accountId: String,
    @SerializedName("code") val code: Int,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("rePassword") val rePassword: String
)
