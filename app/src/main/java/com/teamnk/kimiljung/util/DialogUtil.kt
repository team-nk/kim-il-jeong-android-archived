package com.teamnk.kimiljung.util

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

// TODO title, descriptions parameter
fun showDialogWithSingleButton(context: Context, activity: Activity) {
    val binding: DialogAllSingleButtonBinding by lazy {
        DialogAllSingleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    /*val dialogBuilder = AlertDialog.Builder(context, R.layout.dialog_all_single_button)
        .setView(binding.root)
        .setCancelable(false)
        .create()*/

    val dialog = Dialog(context)
    dialog.setContentView(binding.root)
    dialog.setCancelable(false)

    dialog.show()

    // TODO redefine things with parameter
    binding.tvDialogSingleTitle.setText(R.string.dialog_register_success_title)
    binding.tvDialogSingleDescription.setText(R.string.dialog_register_success_description)
    binding.btnDialogAccept.setOnClickListener {
        dialog.cancel()
    }
}