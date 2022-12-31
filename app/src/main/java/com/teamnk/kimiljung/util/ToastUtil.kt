package com.teamnk.kimiljung.util

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity

internal fun AppCompatActivity.showToast(
    text: String, length: Int,
) {
    Toast.makeText(this, text, length).show()
}

internal fun AppCompatActivity.showShortToast(text: String) {
    showToast(
        text = text,
        length = LENGTH_SHORT,
    )
}

internal fun AppCompatActivity.showLongToast(text: String) {
    showToast(
        text = text,
        length = LENGTH_LONG,
    )
}
