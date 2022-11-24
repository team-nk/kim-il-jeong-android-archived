package com.teamnk.kimiljung.feature.fragment.calendar

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar
) {

    private val seoul: LatLng by lazy {
        LatLng(37.554891, 126.970814)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendarView()
        initCreateScheduleImage()
        initMapLocationDialog(savedInstanceState)
    }

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

    private fun initCalendarView() {
        with(binding.calendarCalendarView) {
            addDecorator(TodayDecorator(requireActivity()))
            setWeekDayFormatter(
                ArrayWeekDayFormatter(
                    resources.getStringArray(
                        R.array.calendar_week
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

    private fun initCreateScheduleImage() {
        binding.imgCalendarScheduleCreate.setOnClickListener {
            //Todo
        }
    }

    override fun observeEvent() {}
}

