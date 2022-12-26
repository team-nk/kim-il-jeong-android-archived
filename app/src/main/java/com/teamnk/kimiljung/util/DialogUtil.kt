package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teamnk.kimiljung.databinding.DialogCreateScheduleBinding
import com.teamnk.kimiljung.databinding.DialogDoubleButtonBinding
import com.teamnk.kimiljung.databinding.DialogSearchLocationBinding
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

    val dialog = initDialog(
        context = context,
        binding = binding,
    )

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

    val dialog = initDialog(
        context = context,
        binding = binding,
    )

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

fun showScheduleCreateDialog(
    context : Context,
){
    val binding: DialogCreateScheduleBinding by lazy {
        DialogCreateScheduleBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialog = initBottomSheetDialog(
        context = context,
        binding = binding,
    )

    binding.tvDialogCreateScheduleSearchLocation.setOnClickListener {
        showSearchLocationDialog(
            context = context,
        )
    }
}

fun showSearchLocationDialog(
    context : Context,
){
    val binding : DialogSearchLocationBinding by lazy {
        DialogSearchLocationBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialog = initBottomSheetDialog(
        context = context,
        binding = binding,
    )
}

private fun initDialog(
    context : Context,
    binding : ViewDataBinding
) : Dialog =
    Dialog(context).apply {
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }

private fun initBottomSheetDialog(
    context : Context,
    binding : ViewDataBinding
) : BottomSheetDialog =
    BottomSheetDialog(context).apply {
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        show()
    }