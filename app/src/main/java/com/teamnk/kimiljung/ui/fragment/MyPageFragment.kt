package com.teamnk.kimiljung.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding

class MyPageFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_mypage
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
        TODO("Not yet implemented")
    }

    private fun initChangePasswordButton() {
        TODO("Not yet implemented")
    }

    private fun initApplicationInformationButton() {
        TODO("Not yet implemented")
    }

    private fun initEditBirthDayButton() {
        TODO("Not yet implemented")
    }
}