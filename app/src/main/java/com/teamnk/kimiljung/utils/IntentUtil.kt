package com.teamnk.kimiljung.utils

import android.content.Context
import android.content.Intent

object IntentUtil {
    fun <T> startIntent(context: Context, to: Class<T>){
        context.startActivity(Intent(context, to))
    }

    fun <T> startIntentClearTop(context: Context, to: Class<T>){
        val intent = Intent(context, to)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}