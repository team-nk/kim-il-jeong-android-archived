package com.teamnk.kimiljung.common

import android.app.Application
import com.teamnk.kimiljung.util.SharedPreferencesName
import com.teamnk.kimiljung.util.defaultSharedPreferences

class KimIlJeongApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        defaultSharedPreferences = getSharedPreferences(
            SharedPreferencesName.DEFAULT,
            MODE_PRIVATE,
        )
    }
}