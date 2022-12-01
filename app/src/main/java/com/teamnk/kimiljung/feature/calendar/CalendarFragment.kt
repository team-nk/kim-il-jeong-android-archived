package com.teamnk.kimiljung.feature.calendar

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.model.LatLng
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.util.TodayDecorator

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar
) {

    // TODO remove in future
    private val latlngSeoul: LatLng by lazy {
        LatLng(37.554891, 126.970814)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendarView()
        initAddScheduleButton()
    }


    private fun initCalendarView() {
        binding.calendarViewFragmentCalendarMain.apply {
            addDecorator(TodayDecorator(requireActivity()))
            setWeekDayFormatter(
                ArrayWeekDayFormatter(
                    resources.getStringArray(
                        R.array.fragment_calendar_string_array_calendar_week
                    )
                )
            )
            setTitleFormatter {
                return@setTitleFormatter "${it.year}년 ${it.month + 1}월"
            }
            setOnDateChangedListener { _, date, _ ->
                if (date == CalendarDay.today()) {
                    removeDecorators()
                } else {
                    addDecorator(TodayDecorator(requireActivity()))
                }
            }
        }
    }

    private fun initAddScheduleButton() {
        binding.btnFragmentCalendarAddSchedule.setOnClickListener {
            //TODO When Create Button Clicked
        }
    }

    override fun observeEvent() {}
}

/*
private fun initMapLocationDialog(
    savedInstanceState: Bundle?,
) {
    binding.btnCalendarTest.setOnClickListener {
        BottomSheetDialog(requireActivity()).run {
            setContentView(
                layoutInflater.inflate(R.layout.dialog_map_location, null)
            )
            show()
            findViewById<Button>(R.id.btn_map_delete)?.setOnClickListener {
                // TODO 일정 삭제 로직 구현
            }
            findViewById<MapView>(R.id.mv_map_location)?.run {
                onCreate(savedInstanceState)
                it.run {
                    getMapAsync {
                        it.run {
                            addMarker(
                                MarkerOptions()
                                    .title("서울")
                                    .position(
                                        LatLng(
                                            37.554891,
                                            126.970814,
                                        )
                                    )
                            )
                            setMinZoomPreference(14F)
                            setMaxZoomPreference(18F)
                            moveCamera(
                                CameraUpdateFactory.newLatLng(
                                    LatLng(
                                        37.554891,
                                        126.970814,
                                    )
                                )
                            )
                            animateCamera(
                                CameraUpdateFactory.zoomTo(4F)
                            )
                            mapType = GoogleMap.MAP_TYPE_NORMAL
                        }
                    }
                }
                onStart()
                onResume()
            }
        }
    }
}

 */
