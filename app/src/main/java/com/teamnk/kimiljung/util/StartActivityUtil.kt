package com.teamnk.kimiljung.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*

private fun <T : Activity> Activity.startActivity(
    context: Context,
    to: Class<out T>,
    flag: Int,
) {
    startActivity(
        Intent(
            /* packageContext = */
            context,
            /* cls = */
            to,
        ).addFlags(
            /* flags = */
            flag,
        )
    )
}

/**
 * @author junsuPark
 * default startActivity function, which let context activity maintain single activity.
 */
internal fun <T : Activity> Activity.startActivity(
    context: Context,
    to: Class<out T>,
) {
    startActivity(
        context = context,
        to = to,
        flag = FLAG_ACTIVITY_SINGLE_TOP,
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
    finish()
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
@Deprecated(
    "Legacy Code", ReplaceWith(
        "Activity.startActivity(Context, Class)",
        "android.content.Intent",
    )
)
fun <T> startActivityLegacy(context: Context, to: Class<T>) {
    context.startActivity(Intent(context, to))
}

fun <T> startActivityRemovingBackStack(context: Context, to: Class<T>) {
    context.startActivity(
        Intent(
            context, to
        ).addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
    )
}
