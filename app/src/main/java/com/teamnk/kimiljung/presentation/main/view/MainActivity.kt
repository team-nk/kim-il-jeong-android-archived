package com.teamnk.kimiljung.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.data.repository.main.MainRepository
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.presentation.base.BaseActivity
import com.teamnk.kimiljung.presentation.main.fragment.calendar.view.CalendarFragment
import com.teamnk.kimiljung.presentation.main.fragment.map.view.MapFragment
import com.teamnk.kimiljung.presentation.main.fragment.mypage.view.MyPageFragment
import com.teamnk.kimiljung.presentation.main.fragment.notification.view.NotificationFragment
import com.teamnk.kimiljung.presentation.main.viewmodel.MainViewModel
import com.teamnk.kimiljung.presentation.main.viewmodel.MainViewModelFactory
import com.teamnk.kimiljung.util.SharedPreferencesKey.BOTTOM_NAVIGATION_CALENDAR_ID
import com.teamnk.kimiljung.util.SharedPreferencesKey.BOTTOM_NAVIGATION_MAP_ID
import com.teamnk.kimiljung.util.SharedPreferencesKey.BOTTOM_NAVIGATION_MY_PAGE_ID
import com.teamnk.kimiljung.util.SharedPreferencesKey.BOTTOM_NAVIGATION_NOTIFICATION_ID
import com.teamnk.kimiljung.util.SharedPreferencesKey.MAIN_ACTIVITY_SAVED_BOTTOM_NAVIGATION_ID
import com.teamnk.kimiljung.util.SharedPreferencesName.MAIN_ACTIVITY
import com.teamnk.kimiljung.util.initializeSharedPreferences
import com.teamnk.kimiljung.util.putInSharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MainRepository()))[MainViewModel::class.java]
    }

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
    private val myPageFragment by lazy {
        MyPageFragment()
    }

    private var selectedBottomNavigationMenuId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedBottomNavigationMenuId =
            sharedPreferences.getInt(
                MAIN_ACTIVITY_SAVED_BOTTOM_NAVIGATION_ID,
                BOTTOM_NAVIGATION_CALENDAR_ID
            )

        initBottomNavigationView()
        initFragment()
    }

    private fun initFragment() {
        changeFragment(
            getFragmentFromBottomNavigationMenuId(selectedBottomNavigationMenuId)
        )
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

            selectedItemId = selectedBottomNavigationMenuId!!
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, fragment)
            .commitAllowingStateLoss()

        selectedBottomNavigationMenuId = getSelectedBottomNavigationMenuIdFromFragment(fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        putInSharedPreferences(
            sharedPreferencesEditor,
            MAIN_ACTIVITY_SAVED_BOTTOM_NAVIGATION_ID,
            selectedBottomNavigationMenuId
        )
    }

    private fun getSelectedBottomNavigationMenuIdFromFragment(fragment: Fragment): Int {
        return when (fragment) {
            mapFragment -> BOTTOM_NAVIGATION_MAP_ID
            notificationFragment -> BOTTOM_NAVIGATION_NOTIFICATION_ID
            myPageFragment -> BOTTOM_NAVIGATION_MY_PAGE_ID
            else -> BOTTOM_NAVIGATION_CALENDAR_ID
        }
    }

    private fun getFragmentFromBottomNavigationMenuId(id: Int?): Fragment {
        return when (id) {
            BOTTOM_NAVIGATION_MAP_ID -> mapFragment
            BOTTOM_NAVIGATION_NOTIFICATION_ID -> notificationFragment
            BOTTOM_NAVIGATION_MY_PAGE_ID -> myPageFragment
            else -> calendarFragment
        }
    }

    override fun observeEvent() {}
}