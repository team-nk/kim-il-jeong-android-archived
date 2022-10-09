package com.teamnk.kimiljung.data

import com.teamnk.kimiljung.data.api.LoginAPI
import com.teamnk.kimiljung.data.api.RegisterAPI
import retrofit2.Retrofit

private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl(BASE_URL)
    // TODO GSON
}.build()

val loginAPI: LoginAPI by lazy {
    retrofit.create(LoginAPI::class.java)
}

val registerAPI: RegisterAPI by lazy {
    retrofit.create(RegisterAPI::class.java)
}