package com.teamnk.kimiljung.api

var accessToken: String? = null
var refreshToken: String? = nulldata class TokenResponse(

interface TokenAPI {

    @PUT("/auth")
    fun renewToken(): Response<TokenResponse>
}

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)