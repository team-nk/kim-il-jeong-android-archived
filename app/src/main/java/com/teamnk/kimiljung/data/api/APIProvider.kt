package com.teamnk.kimiljung.data.api

val loginAPI: LoginAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(LoginAPI::class.java)
}

val registerAPI: RegisterAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(RegisterAPI::class.java)
}