package com.teamnk.kimiljung.util

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.teamnk.kimiljung.R

class TodayDecorator(context: Context) : DayViewDecorator {

    private val date = CalendarDay.today()
    private val drawable =
        AppCompatResources.getDrawable(context, R.drawable.background_calendar_date_today)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == date
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(drawable!!)
    }
}