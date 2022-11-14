package com.teamnk.kimiljung.feature.fragment.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.teamnk.kimiljung.R

class TodayDecorator(context: Context) : DayViewDecorator {

    private val date = CalendarDay.today()

    private val drawable = AppCompatResources.getDrawable(context, R.drawable.calendar_select)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(drawable!!)
    }
}