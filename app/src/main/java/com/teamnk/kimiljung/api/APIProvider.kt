package com.teamnk.kimiljung.api

import com.teamnk.kimiljung.feature.login.LoginAPI
import com.teamnk.kimiljung.feature.register.RegisterAPI

val loginAPIProvider: LoginAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(LoginAPI::class.java)
}

val registerAPIProvider: RegisterAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(RegisterAPI::class.java)
}