package com.teamnk.kimiljung.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMypageBinding
import com.teamnk.kimiljung.ui.activity.MainActivity
import com.teamnk.kimiljung.ui.activity.auth.StartActivity
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initPersonalInformationButtons()
        initInteractButtons()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // 유저 프로필 정보, 내 일정 확인하기, 내가 쓴 게시물 보기 등 개인 정보를 담고 있는 레이아웃과 내부 버튼들을 초기화하는 함수
    private fun initPersonalInformationButtons() {
    }

    // 생년월일 입력하기/수정하기, 로그아웃 등의 버튼을 초기화하는 함수
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
        }
    }

    private fun initChangePasswordButton() {
    }

    private fun initApplicationInformationButton() {
    }

    private fun initEditBirthDayButton() {
    }
}