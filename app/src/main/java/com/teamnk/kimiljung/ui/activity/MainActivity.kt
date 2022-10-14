package com.teamnk.kimiljung.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.ui.fragment.CalendarFragment
import com.teamnk.kimiljung.ui.fragment.MapFragment
import com.teamnk.kimiljung.ui.fragment.MyPageFragment
import com.teamnk.kimiljung.ui.fragment.NotificationFragment
import com.teamnk.kimiljung.util.SAVED_FRAGMENT_ID
import com.teamnk.kimiljung.util.SharedPreferencesKey
import com.teamnk.kimiljung.util.SharedPreferencesKey.MAIN_ACTIVITY_SAVED_FRAGMENT_ID
import com.teamnk.kimiljung.util.SharedPreferencesName.INTRODUCTION_PAGER_ACTIVITY
import com.teamnk.kimiljung.util.SharedPreferencesName.MAIN_ACTIVITY
import com.teamnk.kimiljung.util.initializeSharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val sharedPreferences by lazy {
        initializeSharedPreferences(this, MAIN_ACTIVITY, MODE_PRIVATE)
    }
    private val sharedPreferencesEditor by lazy {
        sharedPreferences.edit()
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
    private val userFragment by lazy {
        MyPageFragment()
    }

    private lateinit var selectedFragment: Fragment

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

            selectedItemId = sharedPreferences.getInt(MAIN_ACTIVITY_SAVED_FRAGMENT_ID, R.id.bn_main_calendar)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()

        selectedFragment = fragment ?: calendarFragment
    }

    override fun observeEvent() {}
}