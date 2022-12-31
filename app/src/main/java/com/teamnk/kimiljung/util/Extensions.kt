package com.teamnk.kimiljung.util

import android.view.View

internal fun View.disable() {
    alpha = VIEW_ALPHA_DISABLED
    isEnabled = false
}

const val VIEW_ALPHA_DISABLED = 0.4f