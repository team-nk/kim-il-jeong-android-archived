package com.teamnk.kimiljung.feature.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.feature.fragment.calendar.CalendarFragment
import com.teamnk.kimiljung.feature.fragment.map.MapFragment
import com.teamnk.kimiljung.feature.fragment.mypage.MyPageFragment
import com.teamnk.kimiljung.feature.fragment.post.PostFragment
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.SharedPreferencesName
import com.teamnk.kimiljung.util.initializeSharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val userAuthSharedPreferences by lazy {
        initializeSharedPreferences(this, SharedPreferencesName.USER_AUTH, MODE_PRIVATE)
    }

    private val userAuthSharedPreferencesEditor: SharedPreferences.Editor by lazy {
        userAuthSharedPreferences.edit()
    }

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
    private val postFragment by lazy {
        PostFragment()
    }
    private val myPageFragment by lazy {
        MyPageFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLoggedIn()

        initBottomNavigationView()
    }

    private fun checkLoggedIn() {
        if (!userAuthSharedPreferences.getBoolean(IS_LOGGED_IN, false)) {
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
                        changeFragment(postFragment)
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
