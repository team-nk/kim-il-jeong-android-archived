package com.teamnk.kimiljung.feature.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.feature.changepassword.ChangePasswordActivity
import com.teamnk.kimiljung.feature.changeuserinformation.ChangeUserInformationActivity
import com.teamnk.kimiljung.feature.enterbirthday.EnterBirthdayBottomSheetDialogFragment
import com.teamnk.kimiljung.feature.start.StartActivity
import com.teamnk.kimiljung.util.loadImage
import com.teamnk.kimiljung.util.showDialogWithDoubleButton
import com.teamnk.kimiljung.util.showShortSnackBar
import com.teamnk.kimiljung.util.startActivityRemovingBackStack

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage
) {

    private lateinit var changePasswordActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var changeUserInformationActivityResultLauncher: ActivityResultLauncher<Intent>

    private val viewModel by lazy {
        ViewModelProvider(
            requireActivity(), MyPageViewModelFactory(
                MyPageRepository(),
                requireActivity().application,
            )
        )[MyPageViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActivityResultLaunchers()

        initPersonalInformationButtons()
        initInteractButtons()

        viewModel.getSelfInformation()
    }

    private fun initActivityResultLaunchers() {
        changePasswordActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == RESULT_OK) {
                if (it.data?.getBooleanExtra("isChangePasswordSuccess", false) == true) {
                    showShortSnackBar(
                        view = binding.root,
                        getString(R.string.fragment_mypage_change_password_success),
                    )
                }
            }
        }

        changeUserInformationActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == RESULT_OK) {
                //TODO 유저 정보 재호출 로직
            }
        }
    }

    private fun initPersonalInformationButtons() {
        initEditProfileButton()
    }

    private fun initEditProfileButton() {
        binding.btnFragmentMypageEditProfile.setOnClickListener {
            changeUserInformationActivityResultLauncher.launch(
                Intent(
                    requireActivity(), ChangeUserInformationActivity::class.java,
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
        binding.btnFragmentMypageLogout.setOnClickListener {
            showDialogWithDoubleButton(
                context = requireActivity(),
                title = getString(R.string.fragment_mypage_dialog_are_you_sure_you_log_out),
                actionText = getString(R.string.log_out),
            ) {
                logOut()
            }
        }
    }

    private fun logOut() {
        with(defaultSharedPreferencesEditor) {
            clear()
            apply()
        }
        startActivityRemovingBackStack(
            requireActivity(),
            StartActivity::class.java,
        )
    }

    private fun initChangePasswordButton() {
        binding.btnFragmentMypageChangePassword.setOnClickListener {
            changeUserInformationActivityResultLauncher.launch(
                Intent(
                    requireActivity(),
                    ChangePasswordActivity::class.java,
                )
            )
        }
    }

    private fun initApplicationInformationButton() {}

    private fun initEditBirthDayButton() {
        binding.btnFrgamentMypageEditBirthday.setOnClickListener {
            EnterBirthdayBottomSheetDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    tag,
                )
            }
        }
    }

    override fun observeEvent() {
        viewModel.selfInformation.observe(
            viewLifecycleOwner
        ) {
            initSelfInformationView(it)
        }

        viewModel.snackBarMessage.observe(
            viewLifecycleOwner
        ) {
            showShortSnackBar(
                binding.root,
                it,
            )
        }
    }

    private fun initSelfInformationView(selfInformationResponse: GetSelfInformationResponse) {
        with(binding) {
            imageFragmentMypageUserProfile.loadImage(selfInformationResponse.profileImageURL.run {
                if (this@run == "'a'") {
                    "https://avatars.githubusercontent.com/u/101160207?s=80&v=4"
                } else {
                    this@run
                }
            })
            tvFragmentMypageEmail.text = selfInformationResponse.email
            tvFragmentMypageId.text = selfInformationResponse.accountId
        }
    }
}
