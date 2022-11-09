package com.teamnk.kimiljung.presentation.fragment.calendar.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.presentation.base.BaseFragment
import com.teamnk.kimiljung.presentation.fragment.calendar.CalendarViewManager
import com.teamnk.kimiljung.presentation.main.view.MainActivity
import java.util.*

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar
) {

    private lateinit var mainActivity: MainActivity

    private var today = CalendarDay.today()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = context as MainActivity

        initCalendarView()
        initCreateScheduleImage()
    }

    private fun initCalendarView() {

        binding.calendarCalendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getStringArray(R.array.calendar_week)))
        binding.calendarCalendarView.addDecorator(CalendarViewManager(mainActivity))

        binding.calendarCalendarView.setTitleFormatter(object : TitleFormatter{
            override fun format(day: CalendarDay): CharSequence {
                val date = day.date
                var month = day.month
                var calendarHeaderElements = date.toString().split(" ")
                month = month + 1
                if(month == 13) month = 1
                val calendarHeaderBuilder = StringBuilder().append("${calendarHeaderElements[5]} ${month}월")
                return calendarHeaderBuilder.toString()
            }
        })

        with(binding.calendarCalendarView) {
            setOnDateChangedListener { _, date, _ ->
                if (date == today) {
                    removeDecorator(CalendarViewManager(mainActivity))
                } else {
                    addDecorator(CalendarViewManager(mainActivity))
                }
            }
        }
    }

    private fun initCreateScheduleImage(){
        binding.imgCalendarScheduleCreate.setOnClickListener {
            ScheduleFragment().show(parentFragmentManager, ScheduleFragment().tag)
        }
    }

    override fun observeEvent() {
    }
}