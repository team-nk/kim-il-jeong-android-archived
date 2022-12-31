package com.teamnk.kimiljung.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*

internal fun <T : Activity> Activity.startActivity(
    context: Context,
    to: Class<out T>,
    flag: Int = FLAG_ACTIVITY_CLEAR_TOP,
) {
    startActivity(
        Intent(
            /* packageContext = */
            context,
            /* cls = */
            to,
        )
    )
}

internal fun <T : Activity> Activity.startActivityFinishingCurrentActivity(
    context: Context,
    to: Class<out T>,
) {
    startActivity(
        context = context,
        to = to,
    )
}

internal fun <T : Activity> Activity.startActivityRemovingBackStack(
    context: Context,
    to: Class<out T>,
) {
    startActivity(
        context = context,
        to = to,
        flag = FLAG_ACTIVITY_CLEAR_TASK,
    )
}

// todo remove two functions about activity
/**
 * @author junsuPark
 * must be removed after migration of startActivity utility functions.
 */
fun <T> startActivity(context: Context, to: Class<T>) {
    context.startActivity(Intent(context, to))
}

fun <T> startActivityRemovingBackStack(context: Context, to: Class<T>) {
    context.startActivity(
        Intent(
            context, to
        ).addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
    )
}
