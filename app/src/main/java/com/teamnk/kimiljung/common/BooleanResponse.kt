package com.teamnk.kimiljung.common

import com.google.gson.annotations.SerializedName

data class BooleanResponse(
    @SerializedName("is_true") val boolean: Boolean,
)
