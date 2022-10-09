package com.teamnk.kimiljung.data

import retrofit2.Retrofit

private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl(BASE_URL)
    // TODO GSON
}.build()

val loginAPI: LoginApi by lazy {
    retrofit.create(LoginApi::class.java)
}

val registerAPI: RegisterApi by lazy {
    retrofit.create(RegisterApi::class.java)
}