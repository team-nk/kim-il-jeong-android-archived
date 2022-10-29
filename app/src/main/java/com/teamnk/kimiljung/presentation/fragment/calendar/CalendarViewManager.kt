package com.teamnk.kimiljung.presentation.fragment.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.teamnk.kimiljung.R

class CalendarViewManager(context: Context) : DayViewDecorator {
    private var date = CalendarDay.today()

    val drawable = AppCompatResources.getDrawable(context, R.drawable.background_selected_date)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        if (drawable != null) {
            view?.setSelectionDrawable(drawable)
        }
    }
}