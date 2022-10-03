package com.teamnk.kimiljung.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

fun showDialogWithSingleButton(context: Context, activity: Activity) {
    /*val binding: DialogAllSingleButtonBinding by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_all_single_button,
            null,
            false
        )
    }*/
    AlertDialog.Builder(activity)
        .setTitle(R.string.register_userId)
        .setView(LayoutInflater.from(context).inflate(R.layout.dialog_all_single_button, null))
        .show()
}