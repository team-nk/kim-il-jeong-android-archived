package com.teamnk.kimiljung.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*

object StartActivityUtil {
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
    fun <T : Activity> Activity.startActivity(
        context: Context,
        to: Class<out T>,
    ) {
        startActivity(
            context = context,
            to = to,
            flag = listOf(FLAG_ACTIVITY_SINGLE_TOP),
        )
    }

    fun <T : Activity> Activity.startActivityFinishingCurrentActivity(
        context: Context, to: Class<out T>,
        flag: List<Int> = listOf(FLAG_ACTIVITY_SINGLE_TOP),
    ) {
        startActivity(
            context = context,
            to = to,
        )
        finish()
    }

    fun <T : Activity> Activity.startActivityRemovingBackStack(
        context: Context,
        to: Class<out T>,
    ) {
        startActivityFinishingCurrentActivity(
            context = context,
            to = to,
            flag = listOf(FLAG_ACTIVITY_CLEAR_TASK, FLAG_ACTIVITY_NEW_TASK),
        )
    }
}