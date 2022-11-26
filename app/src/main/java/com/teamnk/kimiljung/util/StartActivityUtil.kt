package com.teamnk.kimiljung.util

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

fun <T> startActivity(context: Context, to: Class<T>) {
    context.startActivity(Intent(context, to))
}

fun <T> startActivityRemovingBackStack(context: Context, to: Class<T>) {
    context.startActivity(Intent(context, to).addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK))
}