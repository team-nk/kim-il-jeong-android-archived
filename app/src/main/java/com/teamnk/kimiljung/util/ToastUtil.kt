package com.teamnk.kimiljung.util

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.LENGTH_LONG

fun showShortToast(context: Context, text: String) {
    showToast(context, text, LENGTH_SHORT)
}

fun showLongToast(context: Context, text: String) {
    showToast(context, text, LENGTH_LONG)
}

fun showToast(context: Context, text: String, length: Int) {
    Toast.makeText(context, text, length).show()
}
