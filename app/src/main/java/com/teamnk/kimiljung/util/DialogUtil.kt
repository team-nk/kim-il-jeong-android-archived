package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.teamnk.kimiljung.databinding.DialogAllDoubleButtonBinding
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

fun showDialogWithSingleButton(
    context: Context,
    title: String,
    description: String,
    functionWhenAcceptButtonClicked: () -> Unit
) {

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
        btnDialogSingleAccept.setOnClickListener {
            functionWhenAcceptButtonClicked()

            dialog.dismiss()
        }
    }
}

fun showDialogWithDoubleButton(
    context: Context,
    title: String,
    primaryText: String,
    secondaryText: String,
    functionWhenPrimaryButtonClicked: () -> Unit,
    functionWhenSecondaryButtonClicked: () -> Unit
) {

    val binding: DialogAllDoubleButtonBinding by lazy {
        DialogAllDoubleButtonBinding.inflate(
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
        tvDialogDoubleTitle.text = title
        btnDialogDoublePrimary.text = primaryText
        btnDialogDoublePrimary.setOnClickListener {
            //todo

            dialog.dismiss()
        }
        btnDialogDoubleSecondary.text = secondaryText
        btnDialogDoubleSecondary.setOnClickListener {
            //todo

            dialog.dismiss()
        }
    }
}