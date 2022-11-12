package com.teamnk.kimiljung.feature.fragment.calendar

import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentScheduleBinding
import com.teamnk.kimiljung.base.BaseBottomSheetDialogFragment

class ScheduleFragment : BaseBottomSheetDialogFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreateSchduleBottomSheetDialog()
    }

    private fun initCreateSchduleBottomSheetDialog(){
        binding.tvScheduleDoCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun observeEvent() {}
}