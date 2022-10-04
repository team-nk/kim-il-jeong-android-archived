package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Toast
import com.teamnk.kimiljung.databinding.DialogAllSingleButtonBinding

fun showDialogWithSingleButton(context: Context, title: String, description: String, functionWhenAcceptButtonClicked: () -> Unit) {
    val binding: DialogAllSingleButtonBinding by lazy {
        DialogAllSingleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialog = Dialog(context)
    with(dialog) {
        setContentView(binding.root)
        setCancelable(false)
        show()
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    binding.tvDialogSingleTitle.text = title
    binding.tvDialogSingleDescription.text = description
    binding.btnDialogAccept.setOnClickListener {
        // todo 로그인 페이지 로직
        functionWhenAcceptButtonClicked()
    }
}