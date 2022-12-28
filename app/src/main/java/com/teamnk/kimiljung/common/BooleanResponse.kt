package com.teamnk.kimiljung.common

import com.google.gson.annotations.SerializedName

data class BooleanResponse(
    @SerializedName("true") val boolean: Boolean,
)
