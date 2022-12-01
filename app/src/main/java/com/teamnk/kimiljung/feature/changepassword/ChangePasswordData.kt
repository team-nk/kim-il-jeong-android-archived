package com.teamnk.kimiljung.feature.changepassword

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("nowPassword") val oldPassword: String,
    @SerializedName("newPassword") val newPassword: String,
    @SerializedName("newPasswordRepeat") val newPasswordRepeat: String,
)
