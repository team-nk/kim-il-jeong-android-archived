package com.teamnk.kimiljung.util

import com.teamnk.kimiljung.R

object SharedPreferencesName {
    const val INTRODUCTION_PAGER_ACTIVITY = "introductionPagerActivity"

    const val MAIN_ACTIVITY = "mainActivity"

    const val USER_AUTH = "userAuth"
}

object SharedPreferencesKey {
    const val INTRODUCTION_PAGER_IS_INTRODUCTION_PAGER_ACTIVITY_SHOWN =
        "isIntroductionPagerActivityShown"

    const val MAIN_ACTIVITY_SAVED_BOTTOM_NAVIGATION_ID = "savedBottomNavigationId"
    const val BOTTOM_NAVIGATION_CALENDAR_ID = R.id.bn_main_calendar
    const val BOTTOM_NAVIGATION_MAP_ID = R.id.bn_main_map
    const val BOTTOM_NAVIGATION_NOTIFICATION_ID = R.id.bn_main_notification
    const val BOTTOM_NAVIGATION_MY_PAGE_ID = R.id.bn_main_mypage
}

