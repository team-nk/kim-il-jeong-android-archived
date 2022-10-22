package com.teamnk.kimiljung.presentation.main.mypage.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.presentation.main.fragment.base.BaseFragment
import com.teamnk.kimiljung.presentation.main.view.MainActivity
import com.teamnk.kimiljung.presentation.start.view.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesName
import com.teamnk.kimiljung.util.clearSharedPreferences
import com.teamnk.kimiljung.util.showDialogWithDoubleButton
import com.teamnk.kimiljung.util.startIntentClearTop

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage
) {

    private lateinit var mainActivity: MainActivity

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

            logOut()
        }
    }

    private fun logOut() {
        // TODO 리팩토링 하기
        clearSharedPreferences(mainActivity, SharedPreferencesName.USER_AUTH, MODE_PRIVATE)
        clearSharedPreferences(mainActivity, SharedPreferencesName.MAIN_ACTIVITY, MODE_PRIVATE)
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