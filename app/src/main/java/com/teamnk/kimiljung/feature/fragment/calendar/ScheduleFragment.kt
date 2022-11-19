package com.teamnk.kimiljung.feature.fragment.calendar

import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseBottomSheetDialogFragment
import com.teamnk.kimiljung.databinding.DialogScheduleAddictionBinding

class ScheduleFragment :
    BaseBottomSheetDialogFragment<DialogScheduleAddictionBinding>(R.layout.dialog_schedule_addiction) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreateScheduleBottomSheetDialog()
    }

    private fun initCreateScheduleBottomSheetDialog() {
        binding.btnDialogScheduleAddictionCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun observeEvent() {}
}