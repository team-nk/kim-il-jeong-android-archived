package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.teamnk.kimiljung.databinding.DialogDoubleButtonBinding
import com.teamnk.kimiljung.databinding.DialogSingleButtonBinding

fun showDialogWithSingleButton(
    context: Context,
    title: String,
    description: String,
    functionWhenAcceptButtonClicked: () -> Unit
) {

    val binding: DialogSingleButtonBinding by lazy {
        DialogSingleButtonBinding.inflate(
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
        tvDialogSingleButtonTitle.text = title
        tvDialogSingleButtonContent.text = description
        btnDialogSingleButtonAction.setOnClickListener {
            functionWhenAcceptButtonClicked()

            dialog.dismiss()
        }
    }
}

fun showDialogWithDoubleButton(
    context: Context,
    title: String,
    actionText: String,
    functionWhenPrimaryButtonClicked: () -> Unit
) {

    val binding: DialogDoubleButtonBinding by lazy {
        DialogDoubleButtonBinding.inflate(
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
        tvDialogDoubleButtonTitle.text = title
        btnDialogDoubleButtonAction.apply {
            text = actionText
            setOnClickListener {
                functionWhenPrimaryButtonClicked()

                dialog.dismiss()
            }
        }
        btnDialogDoubleButtonCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}