package com.teamnk.kimiljung.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.ui.DecorateCalendarView

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar
) {

    private var today = CalendarDay.today()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarCalendarView.addDecorator(DecorateCalendarView(this.requireContext()))

        binding.calendarCalendarView.setOnDateChangedListener(object : OnDateSelectedListener{
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                Log.d("TEST", date.toString())
                if(date == today){
                    binding.calendarCalendarView.removeDecorator(DecorateCalendarView(this@CalendarFragment.requireContext()))
                    Log.d("TEST", "today")
                }else {
                    binding.calendarCalendarView.addDecorator(DecorateCalendarView(this@CalendarFragment.requireContext()))
                }
            }

        })

    }



    override fun observeEvent() {
    }
}