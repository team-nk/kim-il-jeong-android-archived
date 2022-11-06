package com.teamnk.kimiljung.presentation.fragment.calendar.view

import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentScheduleBinding
import com.teamnk.kimiljung.presentation.base.BaseBottomSheetDialogFragment

class ScheduleFragment : BaseBottomSheetDialogFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreateSchedule()
    }

    private fun initCreateSchedule(){
        binding.tvScheduleDoCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun observeEvent() {}
}