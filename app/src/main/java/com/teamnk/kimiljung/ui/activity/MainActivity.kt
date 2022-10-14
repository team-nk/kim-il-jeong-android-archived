package com.teamnk.kimiljung.ui.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.ui.fragment.CalendarFragment
import com.teamnk.kimiljung.ui.fragment.MapFragment
import com.teamnk.kimiljung.ui.fragment.MyPageFragment
import com.teamnk.kimiljung.ui.fragment.NotificationFragment
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
                    }
                    R.id.bn_main_map -> {
                        changeFragment(mapFragment)
                    }
                    R.id.bn_main_notification -> {
                        changeFragment(notificationFragment)
                    }
                    R.id.bn_main_mypage -> {
                        changeFragment(myPageFragment)
                    }
                }
                true
            }

            selectedItemId = selectedBottomNavigationMenuId!!
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()

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