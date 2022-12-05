package com.teamnk.kimiljung.feature.enterbirthday

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseBottomSheetDialogFragment
import com.teamnk.kimiljung.databinding.DialogEnterBirthdayBinding
import com.teamnk.kimiljung.util.showShortSnackBar

class EnterBirthdayBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogEnterBirthdayBinding>(
        R.layout.dialog_enter_birthday,
    ), EnterBirthdayBottomSheetDialogItemClickListener {

    private val viewModel by lazy {
        ViewModelProvider(
            requireActivity(), EnterBirthdayViewModelFactory(
                EnterBirthdayRepository(),
            )
        )[EnterBirthdayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEnterButtonClick()
        onBirthdayButtonClick()
    }

    override fun onEnterButtonClick() {
        binding.btnDialogEnterBirthdayEnter.setOnClickListener {
            if (binding.btnDialogEnterBirthdaySelectBirthday.text.isNotBlank()) {
                // Todo use viewModel variable
                viewModel.enterBirthday(
                    EnterBirthdayRequest(
                        binding.btnDialogEnterBirthdaySelectBirthday.text.toString(),
                    )
                )
            } else {
                showShortSnackBar(
                    dialog!!.window!!.decorView,
                    getString(R.string.dialog_enter_birthday_please_select_birthday),
                )
            }
        }
    }

    override fun onBirthdayButtonClick() {
        binding.btnDialogEnterBirthdaySelectBirthday.setOnClickListener {
            // TODO date select calendar dialog, and show selected date
            // save received date at viewModel
            // val birthday = TODO("get birthday")
        }
    }

    override fun observeEvent() {
        viewModel.isEnterBirthdaySuccess.observe(
            requireActivity(),
        ) {
            if (it) {
                // TODO enable change button

            }
        }
    }
}