package com.teamnk.kimiljung.api

import com.google.gson.GsonBuilder
import com.teamnk.kimiljung.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        return retrofit ?: Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create(),
            )
        ).build()
    }

object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization",
                accessToken!!,
            ).build(),
        )
    }
}

object ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).also {
            when (it.code()) {
                UNAUTHORIZED -> {
                    renewToken()
                }
                else -> {
                    // other processing logic
                }
            }
        }
    }
}
}