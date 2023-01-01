package com.teamnk.kimiljung.feature.changeuserinformation

import androidx.activity.viewModels
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityChangeUserInformationBinding
import com.teamnk.kimiljung.util.loadImage

class ChangeUserInformationActivity : BaseActivity<ActivityChangeUserInformationBinding>(
    R.layout.activity_change_user_information,
) {

    private val viewModel by viewModels<ChangeUserInformationViewModel>()

    override fun observeEvent() {
        initView()
    }

    private fun initView() {
        with(binding) {
            intent.run {
                getStringExtra(
                    PROFILE_URL,
                )?.let {
                    imageActivityChangeUserInformationUserProfile.loadImage(it)
                }
                getStringExtra(
                    EMAIL,
                )?.let {
                    etActivityChangeUserInformationEmail.hint = it
                }
                getStringExtra(
                    ID,
                )?.let {
                    etActivityChangeUserInformationId.hint = it
                }
            }
        }
    }
}


const val PROFILE_URL = "profile_url"
const val EMAIL = "email"
const val ID = "id"