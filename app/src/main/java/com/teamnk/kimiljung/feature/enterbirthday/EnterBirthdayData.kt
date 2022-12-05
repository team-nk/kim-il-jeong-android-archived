package com.teamnk.kimiljung.feature.enterbirthday

import com.google.gson.annotations.SerializedName

data class EnterBirthdayRequest(
    @SerializedName("birthday") val birthday: String,
)