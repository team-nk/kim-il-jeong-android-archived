package com.teamnk.kimiljung.presentation.main.view

import android.content.Intent
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
import com.teamnk.kimiljung.presentation.start.view.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.SharedPreferencesName.DEFAULT
import com.teamnk.kimiljung.util.SharedPreferencesName.USER_AUTH
import com.teamnk.kimiljung.util.initializeSharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private var savedFragmentId: Int? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]
    }

    private val defaultSharedPreferences by lazy {
        initializeSharedPreferences(this, DEFAULT, MODE_PRIVATE)
    }

    private val defaultSharedPreferencesEditor by lazy {
        defaultSharedPreferences.edit()
    }

    private val userAuthSharedPreferences by lazy {
        initializeSharedPreferences(this, USER_AUTH, MODE_PRIVATE)
    }

    private val userAuthSharedPreferencesEditor by lazy {
        userAuthSharedPreferences.edit()
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