package com.teamnk.kimiljung.data

import retrofit2.Retrofit

private val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl(BASE_URL)
    // TODO GSON
}.build()

val loginApi: LoginApi by lazy {
    retrofit.create(LoginApi::class.java)
}