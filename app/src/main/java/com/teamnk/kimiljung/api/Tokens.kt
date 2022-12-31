package com.teamnk.kimiljung.api

var accessToken: String? = null
var refreshToken: String? = nulldata class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)