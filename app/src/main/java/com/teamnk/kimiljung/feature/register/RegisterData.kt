package com.teamnk.kimiljung.feature.register

import com.google.gson.annotations.SerializedName

data class CheckIdDuplicationRequest(
    @SerializedName("accountId") val accountId: String,
)
