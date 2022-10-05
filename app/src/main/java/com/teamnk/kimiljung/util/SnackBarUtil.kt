package com.teamnk.kimiljung.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showShortSnackBar(view: View, text: String){
    showSnackBar(view, text, Snackbar.LENGTH_SHORT)
}

fun showLongSnackBar(view: View, text: String){
    showSnackBar(view, text, Snackbar.LENGTH_LONG)
}

fun showSnackBar(view: View, text: String, length: Int) {
    Snackbar.make(view, text, length).show()
}
