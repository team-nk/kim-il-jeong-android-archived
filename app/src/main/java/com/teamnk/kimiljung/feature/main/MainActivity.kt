package com.teamnk.kimiljung.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.feature.fragment.calendar.CalendarFragment
import com.teamnk.kimiljung.feature.fragment.map.MapFragment
import com.teamnk.kimiljung.feature.fragment.mypage.MyPageFragment
import com.teamnk.kimiljung.feature.fragment.notification.NotificationFragment
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN

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
        checkLoggedIn()

        initBottomNavigationView()

        savedFragmentId = savedInstanceState?.getInt(SAVED_FRAGMENT_ID)
    }

    private fun checkLoggedIn() {
        if (userAuthSharedPreferences.getBoolean(IS_LOGGED_IN, false)) {
            // TODO Login Succeess SnackBar
        } else {
            startActivity(Intent(this, StartActivity::class.java))
        }
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
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_main, fragment)
            .commitAllowingStateLoss()
    }

    override fun observeEvent() {}
}

const val SAVED_FRAGMENT_ID = "SAVED_FRAGMENT"