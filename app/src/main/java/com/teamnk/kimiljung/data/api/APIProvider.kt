package com.teamnk.kimiljung.data.api

import com.teamnk.kimiljung.data.BASE_URL
import retrofit2.Retrofit

// TODO move to object Class
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