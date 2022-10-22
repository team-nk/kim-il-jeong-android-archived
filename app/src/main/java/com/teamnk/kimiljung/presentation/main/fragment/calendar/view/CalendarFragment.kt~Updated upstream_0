package com.teamnk.kimiljung.presentation.calendar.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.presentation.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.presentation.main.calendar.CalendarViewManager
import com.teamnk.kimiljung.presentation.main.view.MainActivity

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
    }

    private fun initCalendarView() {
        with(binding.calendarCalendarView) {
            addDecorator(CalendarViewManager(mainActivity))
            setOnDateChangedListener { _, date, _ ->
                Log.d("TEST", date.toString())
                if (date == today) {
                    removeDecorator(CalendarViewManager(mainActivity))
                    Log.d("TEST", "today")
                } else {
                    addDecorator(CalendarViewManager(mainActivity))
                }
            }
        }
    }


    override fun observeEvent() {
    }
}