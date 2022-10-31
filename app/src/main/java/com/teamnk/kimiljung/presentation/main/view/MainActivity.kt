package com.teamnk.kimiljung.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.data.repository.main.MainRepository
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.fragment.calendar.view.CalendarFragment
import com.teamnk.kimiljung.presentation.fragment.map.view.MapFragment
import com.teamnk.kimiljung.presentation.fragment.mypage.view.MyPageFragment
import com.teamnk.kimiljung.presentation.fragment.notification.view.NotificationFragment
import com.teamnk.kimiljung.presentation.main.viewmodel.MainViewModel
import com.teamnk.kimiljung.presentation.main.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private var savedFragmentId: Int? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]
    }

    private val calendarFragment by lazy {
        CalendarFragment()
    }
    private val mapFragment by lazy {
        MapFragment()
    }
    private val notificationFragment by lazy {
        NotificationFragment()
    }
    private val myPageFragment by lazy {
        MyPageFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigationView()

    }

    private fun initBottomNavigationView() {
        binding.bnMain.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bn_main_calendar -> {
                        changeFragment(calendarFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.bn_main_map -> {
                        changeFragment(mapFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.bn_main_notification -> {
                        changeFragment(notificationFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.bn_main_mypage -> {
                        changeFragment(myPageFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            selectedItemId = R.id.bn_main_calendar
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_main, fragment)
            .commitAllowingStateLoss()
    }

    override fun observeEvent() {}
}
