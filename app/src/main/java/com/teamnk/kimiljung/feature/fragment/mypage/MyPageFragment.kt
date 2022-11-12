package com.teamnk.kimiljung.feature.fragment.mypage

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.SharedPreferencesName.DEFAULT
import com.teamnk.kimiljung.util.SharedPreferencesName.USER_AUTH
import com.teamnk.kimiljung.util.clearSharedPreferences
import com.teamnk.kimiljung.util.showDialogWithDoubleButton
import com.teamnk.kimiljung.util.startIntentWithRemovingActivityStack

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage
) {

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
                requireActivity(),
                getString(R.string.mypage_logout_confirm),
                getString(R.string.mypage_logout)
            ) { startIntentWithRemovingActivityStack(requireActivity(), StartActivity::class.java) }

            logOut()
        }
    }

    private fun logOut() {
        clearSharedPreferences(requireActivity(), USER_AUTH, MODE_PRIVATE)
        clearSharedPreferences(requireActivity(), DEFAULT, MODE_PRIVATE)
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