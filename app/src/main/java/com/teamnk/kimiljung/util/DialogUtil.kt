package com.teamnk.kimiljung.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

// TODO title, descriptions parameter
fun showDialogWithSingleButton(context: Context, activity: Activity) {
    val binding: DialogAllSingleButtonBinding by lazy {
        DialogAllSingleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialogBuilder = AlertDialog.Builder(context)
        .setView(binding.root)
        .setCancelable(false)
        .create()

    // TODO redefine things with parameter
    binding.tvDialogSingleTitle.setText(R.string.dialog_register_success_title)
    binding.tvDialogSingleDescription.setText(R.string.dialog_register_success_description)
    binding.btnDialogAccept.setOnClickListener {
        dialogBuilder.cancel()
    }

    dialogBuilder.show()
}