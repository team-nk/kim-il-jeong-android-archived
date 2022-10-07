package com.teamnk.kimiljung.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.ui.fragment.CalendarFragment
import com.teamnk.kimiljung.ui.fragment.MapFragment
import com.teamnk.kimiljung.ui.fragment.NotificationFragment
import com.teamnk.kimiljung.ui.fragment.UserFragment

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val calendarFragment by lazy {
        CalendarFragment()
    }
    private val mapFragment by lazy {
        MapFragment()
    }
    private val notificationFragment by lazy {
        NotificationFragment()
    }
    private val userFragment by lazy {
        UserFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigationView()
        initFragment()
    }

    private fun initFragment() {
        changeFragment(calendarFragment)
    }

    private fun initBottomNavigationView() {
        binding.bnMain.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bn_main_calendar -> {
                        changeFragment(calendarFragment)
                    }
                    R.id.bn_main_map -> {
                        changeFragment(mapFragment)
                    }
                    R.id.bn_main_notification -> {
                        changeFragment(notificationFragment)
                    }
                    R.id.bn_main_user -> {
                        changeFragment(userFragment)
                    }
                }
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()
    }

    override fun observeEvent() {}
}