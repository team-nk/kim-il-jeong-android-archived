package com.teamnk.kimiljung.feature.enterbirthday

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseBottomSheetDialogFragment
import com.teamnk.kimiljung.databinding.DialogEnterBirthdayBinding
import com.teamnk.kimiljung.util.showShortSnackBar
import java.util.*

class EnterBirthdayBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogEnterBirthdayBinding>(
        R.layout.dialog_enter_birthday,
    ), EnterBirthdayBottomSheetDialogItemClickListener {

    private var isBirthdaySelected = false

    private val viewModel by lazy {
        ViewModelProvider(
            this, EnterBirthdayViewModelFactory(
                EnterBirthdayRepository(),
                requireActivity().application,
            )
        )[EnterBirthdayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEnterButtonClick()
        onBirthdayButtonClick()
        onCancelButtonClick()
    }

    override fun onEnterButtonClick() {
        binding.btnDialogEnterBirthdayEnter.setOnClickListener {
            if (isBirthdaySelected) {
                viewModel.enterBirthday()
            } else {
                showShortSnackBar(
                    dialog!!.window!!.decorView,
                    getString(R.string.dialog_enter_birthday_please_select_birthday),
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBirthdayButtonClick() {
        binding.btnDialogEnterBirthdaySelectBirthday.setOnClickListener {
            // TODO date select calendar dialog, and show selected date
            // save received date at viewModel
            // val birthday = TODO("get birthday")
            isBirthdaySelected = true

            val today = GregorianCalendar()

            DatePickerDialog(
                requireActivity(),
                { _, selectedYear, selectedMonth, selectedDate ->
                    "${selectedYear.toString().padStart(4, '0')}-${
                        (selectedMonth + 1).toString().padStart(2, '0')
                    }-${selectedDate.toString().padStart(2, '0')}".apply {
                        viewModel.setBirthday(
                            this
                        )
                        binding.btnDialogEnterBirthdaySelectBirthday.text = this
                    }
                },
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DATE),
            ).show()
        }
    }

    override fun onCancelButtonClick() {
        binding.btnDialogEnterBirthdayCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun observeEvent() {
        viewModel.isEnterBirthdaySuccess.observe(
            requireActivity(),
        ) {
            dismiss()
        }

        viewModel.snackBarMessage.observe(
            requireActivity(),
        ) {
            showShortSnackBar(
                binding.root,
                it,
            )
        }
    }
}
