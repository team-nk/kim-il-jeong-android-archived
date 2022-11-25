package com.teamnk.kimiljung.feature.mypage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.showDialogWithDoubleButton
import com.teamnk.kimiljung.util.startActivityRemovingBackStack

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage
) {

    private lateinit var changeUserInformationActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeUserInformationActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    //TODO 유저 정보 재호출 로직
                }
            }

        initPersonalInformationButtons()
        initInteractButtons()
    }

    private fun initPersonalInformationButtons() {
        initEditProfileButton()
    }

    private fun initEditProfileButton() {
        binding.imgMypageEditProfile.setOnClickListener {
            changeUserInformationActivityResultLauncher.launch(
                Intent(
                    requireActivity(), ChangeUserInformationActivity::class.java
                )
            )
        }
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
                context = requireActivity(),
                title = getString(R.string.mypage_logout_confirm),
                primaryText = getString(R.string.mypage_logout),
            ) {
                startActivityRemovingBackStack(requireActivity(), StartActivity::class.java)
            }
            logOut()
        }
    }

    private fun logOut() {
        with(defaultSharedPreferencesEditor) {
            clear()
            apply()
        }
    }

    private fun initChangePasswordButton() {}

    private fun initApplicationInformationButton() {}

    private fun initEditBirthDayButton() {}

    override fun observeEvent() {}
}