package com.teamnk.kimiljung.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityMainBinding
import com.teamnk.kimiljung.fragment.CalendarFragment
import com.teamnk.kimiljung.fragment.MapFragment

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val calendarFragment by lazy {
        CalendarFragment()
    }
    private val mapFragment by lazy {
        MapFragment()
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
}