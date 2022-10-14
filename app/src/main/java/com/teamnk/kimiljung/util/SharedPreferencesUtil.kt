package com.teamnk.kimiljung.util

import android.content.Context
import android.content.SharedPreferences

fun initializeSharedPreferences(context: Context, name: String, mode: Int): SharedPreferences {
    return context.getSharedPreferences(name, mode)
}

fun putInSharedPreferences(
    sharedPreferencesEditor: SharedPreferences.Editor,
    key: String,
    value: Any?
) {
    when (value) {
        is Int -> sharedPreferencesEditor.putInt(key, value).apply()
        is Long -> sharedPreferencesEditor.putLong(key, value).apply()
        is Float -> sharedPreferencesEditor.putFloat(key, value).apply()
        is String -> sharedPreferencesEditor.putString(key, value).apply()
        is Boolean -> sharedPreferencesEditor.putBoolean(key, value).apply()
    }
}