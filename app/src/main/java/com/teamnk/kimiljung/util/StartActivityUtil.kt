package com.teamnk.kimiljung.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*

private fun <T : Activity> Activity.startActivity(
    context: Context, to: Class<out T>,
    flag: List<Int> = listOf(FLAG_ACTIVITY_SINGLE_TOP),
) {
    startActivity(Intent(
        /* packageContext = */
        context,
        /* cls = */
        to,
    ).apply {
        flag.forEach {
            this.addFlags(
                /* flags = */
                it,
            )
        }
    })
}

/**
 * @author junsuPark
 * default internal startActivity function, which let context activity maintain single activity.
 */
internal fun <T : Activity> Activity.startActivity(
    context: Context,
    to: Class<out T>,
) {
    startActivity(
        context = context,
        to = to,
        flag = listOf(FLAG_ACTIVITY_SINGLE_TOP),
    )
}

internal fun <T : Activity> Activity.startActivityFinishingCurrentActivity(
    context: Context, to: Class<out T>,
    flag: List<Int> = listOf(FLAG_ACTIVITY_SINGLE_TOP),
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
    startActivityFinishingCurrentActivity(
        context = context,
        to = to,
        flag = listOf(FLAG_ACTIVITY_CLEAR_TASK, FLAG_ACTIVITY_NEW_TASK),
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
