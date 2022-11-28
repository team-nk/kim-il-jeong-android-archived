package com.teamnk.kimiljung.feature.register

import com.google.gson.annotations.SerializedName

data class CheckIdDuplicationRequest(
    @SerializedName("accountId") val accountId: String,
)

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val code: String,
    @SerializedName("accountId") val accountId: String,
    @SerializedName("password") val password: String,
    @SerializedName("repassword") val repeatPassword: String,
)
