package com.teamnk.kimiljung.util

import android.content.SharedPreferences

internal lateinit var defaultSharedPreferences: SharedPreferences

internal val defaultSharedPreferencesEditor: SharedPreferences.Editor by lazy {
    defaultSharedPreferences.edit()
}

object SharedPreferencesName {
    const val DEFAULT = "default"
}

object SharedPreferencesKey {
    const val IS_LOGGED_IN = "isLoggedIn"
    const val IS_INTRODUCTION_PAGER_SHOWN = "isIntroductionPagerShown"
    const val ACCESS_TOKEN = "accessToken"
    const val REFRESH_TOKEN = "refreshToken"
}