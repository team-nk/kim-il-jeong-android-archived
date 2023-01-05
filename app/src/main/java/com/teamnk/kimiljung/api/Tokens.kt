package com.teamnk.kimiljung.api

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.PUT

var accessToken: String? = null
var refreshToken: String? = null


// todo move alllll logic

interface TokenAPI {

    @PUT("/auth")
    suspend fun renewToken(
        @Header("Refresh-Token") refreshToken: String,
    ): Response<TokenResponse>
}

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)

internal fun renewToken() {
    CoroutineScope(Dispatchers.IO).launch {
        tokenAPIProvider.renewToken(refreshToken!!).also {
            println("Renewing Tokens")
            if (it.isSuccessful) {
                it.body().run {
                    accessToken = this?.accessToken
                    refreshToken = this?.refreshToken
                }
            }
        }
    }
}