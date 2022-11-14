package com.teamnk.kimiljung.feature.fragment.calendar

import android.os.Bundle
import android.view.View
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar
) {

    private var today = CalendarDay.today()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendarView()
        initCreateScheduleImage()
    }

    private fun initCalendarView() {

        binding.calendarCalendarView.setWeekDayFormatter(
            ArrayWeekDayFormatter(
                resources.getStringArray(
                    R.array.calendar_week
                )
            )
        )
        binding.calendarCalendarView.addDecorator(TodayDecorator(requireActivity()))

        binding.calendarCalendarView.setTitleFormatter {
            return@setTitleFormatter "${it.year}년 ${it.month + 1}월"
        }

        with(binding.calendarCalendarView) {
            setOnDateChangedListener { _, date, _ ->
                if (date == today) {
                    removeDecorator(TodayDecorator(requireActivity()))
                } else {
                    addDecorator(TodayDecorator(requireActivity()))
                }
            }
        }
    }

    private fun initCreateScheduleImage() {
        binding.imgCalendarScheduleCreate.setOnClickListener {
            ScheduleFragment().show(parentFragmentManager, ScheduleFragment().tag)
        }
    }

    override fun observeEvent() {
    }
}