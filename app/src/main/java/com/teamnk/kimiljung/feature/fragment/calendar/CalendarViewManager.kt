package com.teamnk.kimiljung.feature.fragment.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.teamnk.kimiljung.R

class CalendarViewManager(context: Context) : DayViewDecorator {
    private var date = CalendarDay.today()

    private val todayDrawable = AppCompatResources.getDrawable(context, R.drawable.calendar_select)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(todayDrawable!!)
    }
}