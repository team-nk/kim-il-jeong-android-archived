package com.teamnk.kimiljung.api

val loginAPIProvider: LoginAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(LoginAPI::class.java)
}

val registerAPIProvider: RegisterAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(RegisterAPI::class.java)
}