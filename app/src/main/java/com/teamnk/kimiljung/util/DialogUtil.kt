package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

fun showDialogWithSingleButton(context: Context, title: String, description: String, functionWhenAcceptButtonClicked: () -> Unit) {

    val binding: DialogAllSingleButtonBinding by lazy {
        DialogAllSingleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }
    val dialog = Dialog(context).apply {
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }
    with(binding) {
        tvDialogSingleTitle.text = title
        tvDialogSingleDescription.text = description
        btnDialogAccept.setOnClickListener {
            functionWhenAcceptButtonClicked()

            dialog.dismiss()
        }
    }
}