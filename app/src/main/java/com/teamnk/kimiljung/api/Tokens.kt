package com.teamnk.kimiljung.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.PUT

var accessToken: String? = null
var refreshToken: String? = null


// todo move alllll logic

interface TokenAPI {

    @PUT("/auth")
    fun renewToken(): Response<TokenResponse>
}

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)

internal suspend fun renewToken() {
    tokenAPIProvider.renewToken().run {
        if (isSuccessful) {
            this.body().let {
                accessToken = it?.accessToken
                refreshToken = it?.refreshToken
            }
        }
    }
}