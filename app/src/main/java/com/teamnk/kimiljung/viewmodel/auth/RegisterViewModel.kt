package com.teamnk.kimiljung.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.data.dto.UserIdDuplicationRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationCodeRequest
import com.teamnk.kimiljung.data.dto.EmailVerificationRequest
import com.teamnk.kimiljung.data.repository.auth.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val repository : RegisterRepository = RegisterRepository()
) : ViewModel() {
    private var success : MutableLiveData<Boolean> = MutableLiveData()
    private var failed : MutableLiveData<Boolean> = MutableLiveData()

    suspend fun verifyEmail(EmailVerificationRequest: EmailVerificationRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                repository.verifyEmail(EmailVerificationRequest)
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
                repository.checkEmailVerificationCode(emailVerificationCodeRequest)
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
                repository.checkUserIdDuplication(userIdDuplicationRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }
}
