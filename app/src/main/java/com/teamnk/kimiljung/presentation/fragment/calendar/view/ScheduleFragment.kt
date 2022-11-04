package com.teamnk.kimiljung.presentation.fragment.calendar.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentScheduleBinding
import com.teamnk.kimiljung.presentation.base.BaseBottomSheetDialogFragment

class ScheduleFragment : BaseBottomSheetDialogFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun observeEvent() {}
}