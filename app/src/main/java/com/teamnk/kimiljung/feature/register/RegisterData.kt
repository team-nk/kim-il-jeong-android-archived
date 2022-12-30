package com.teamnk.kimiljung.feature.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val verificationCode: String,
    @SerializedName("accountId") val accountId: String,
    @SerializedName("password") val password: String,
    @SerializedName("repassword") val repeatPassword: String,
)
