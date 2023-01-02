package com.teamnk.kimiljung.api

import com.google.gson.GsonBuilder
import com.teamnk.kimiljung.BuildConfig
import com.teamnk.kimiljung.api.ResponseCodes.UNAUTHORIZED
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.defaultSharedPreferencesEditor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        return retrofit ?: Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create(),
            )
        ).client(
            OkHttpClient().newBuilder().addInterceptor(RequestInterceptor)
                .addInterceptor(ResponseInterceptor).build(),
        ).build()
    }
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
                    defaultSharedPreferencesEditor.putBoolean(IS_LOGGED_IN, false)
                }
                else -> {
                    // other processing logic
                }
            }
        }
    }
}

object ResponseCodes {

    const val OK = 200
    const val CREATED = 201
    const val ACCEPTED = 202
    const val NO_CONTENT = 204

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405
    const val CONFLICT = 409
    const val TOO_MANY_REQUESTS = 429
}