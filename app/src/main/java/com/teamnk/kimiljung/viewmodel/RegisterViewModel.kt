package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.data.dto.UserIdDuplicationRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationCodeRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationRequest
import com.teamnk.kimiljung.repository.auth.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val rp : RegisterRepository
) : ViewModel() {
    private var success : MutableLiveData<Boolean> = MutableLiveData()
    private var failed : MutableLiveData<Boolean> = MutableLiveData()

    suspend fun verifyEmail(EmailVerificationRequest: EmailVerificationRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.verifyEmail(EmailVerificationRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: EmailVerificationCodeRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.checkEmailVerificationCode(emailVerificationCodeRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: UserIdDuplicationRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.checkUserIdDuplication(userIdDuplicationRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

}
