package com.teamnk.kimiljung.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.ui.activity.MainActivity
import com.teamnk.kimiljung.ui.activity.auth.StartActivity
import com.teamnk.kimiljung.util.*
import com.teamnk.kimiljung.util.SharedPreferencesName.INTRODUCTION_PAGER_ACTIVITY
import com.teamnk.kimiljung.util.SharedPreferencesName.MAIN_ACTIVITY
import com.teamnk.kimiljung.util.SharedPreferencesName.USER_AUTH

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage
) {

    private lateinit var mainActivity: MainActivity

    private val sharedPreferences by lazy {
        getPrivateSharedPreferences(mainActivity, USER_AUTH)
    }

    private val sharedPreferencesEditor by lazy {
        getSharedPreferencesEditor(sharedPreferences)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPersonalInformationButtons()
        initInteractButtons()
    }

    private fun initPersonalInformationButtons() {
    }

    private fun initInteractButtons() {
        initEditBirthDayButton()
        initApplicationInformationButton()
        initChangePasswordButton()
        initLogOutButton()
    }

    private fun initLogOutButton() {
        binding.btnMypageLogout.setOnClickListener {
            showDialogWithDoubleButton(
                mainActivity,
                getString(R.string.mypage_logout_confirm),
                getString(R.string.mypage_logout)
            ) { startIntentClearTop(mainActivity, StartActivity::class.java) }

            // TODO 리팩토링 하기
            clearSharedPreferences(mainActivity, USER_AUTH)
            clearSharedPreferences(mainActivity, MAIN_ACTIVITY)
            clearSharedPreferences(mainActivity, INTRODUCTION_PAGER_ACTIVITY)
        }
    }

    private fun initChangePasswordButton() {
    }

    private fun initApplicationInformationButton() {
    }

    private fun initEditBirthDayButton() {
    }

    override fun observeEvent() {
    }
}