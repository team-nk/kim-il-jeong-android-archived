package com.teamnk.kimiljung.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.ui.DecorateCalendarView
import com.teamnk.kimiljung.ui.activity.MainActivity

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
            addDecorator(DecorateCalendarView(mainActivity))
            setOnDateChangedListener { _, date, _ ->
                Log.d("TEST", date.toString())
                if (date == today) {
                    removeDecorator(DecorateCalendarView(mainActivity))
                    Log.d("TEST", "today")
                } else {
                    addDecorator(DecorateCalendarView(mainActivity))
                }
            }
        }
    }


    override fun observeEvent() {
    }
}