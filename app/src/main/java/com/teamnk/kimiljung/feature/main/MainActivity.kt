package com.teamnk.kimiljung.feature.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.BuildConfig
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.api.accessToken
import com.teamnk.kimiljung.api.refreshToken
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.feature.calendar.CalendarFragment
import com.teamnk.kimiljung.feature.map.MapFragment
import com.teamnk.kimiljung.feature.mypage.MyPageFragment
import com.teamnk.kimiljung.feature.post.PostFragment
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesKey.ACCESS_TOKEN
import com.teamnk.kimiljung.util.SharedPreferencesKey.IS_LOGGED_IN
import com.teamnk.kimiljung.util.SharedPreferencesKey.REFRESH_TOKEN
import com.teamnk.kimiljung.util.StartActivityUtil.startActivityFinishingCurrentActivity
import com.teamnk.kimiljung.util.defaultSharedPreferences
import com.teamnk.kimiljung.util.showShortToast

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]
    }

    private val permissionResult by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it.not()) {
                showShortToast(getString(R.string.activity_introduction_pager_accept_permission))
                startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                    )
                )
            }
        }
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
        saveAccessToken()
        initBottomNavigationView()
        requestPermission()
    }

    private fun checkLoggedIn() {
        if (defaultSharedPreferences.getBoolean(IS_LOGGED_IN, false).not()) {
            startActivityFinishingCurrentActivity(
                context = this,
                to = StartActivity::class.java,
            )
        }
    }

    private fun initBottomNavigationView() {
        with(binding.bottomNavigationViewActivityMain) {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item_bottom_navigation_main_calendar -> {
                        changeFragment(calendarFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_map -> {
                        changeFragment(mapFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_post -> {
                        changeFragment(postFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_mypage -> {
                        changeFragment(myPageFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            selectedItemId = R.id.item_bottom_navigation_main_calendar
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, fragment)
            .commitAllowingStateLoss()
    }

    private fun saveAccessToken() {
        defaultSharedPreferences.run {
            accessToken = this.getString(ACCESS_TOKEN, "")
            refreshToken = this.getString(REFRESH_TOKEN, "")
        }
    }

    private fun requestPermission() {
        permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            10,
        )
    }

    override fun observeEvent() {}
}
