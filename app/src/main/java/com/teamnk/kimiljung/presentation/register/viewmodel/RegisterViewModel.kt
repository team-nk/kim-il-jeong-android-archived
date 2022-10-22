package com.teamnk.kimiljung.presentation.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationCodeRequest
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationRequest
import com.teamnk.kimiljung.data.model.register.RegisterUserIdDuplicationRequest
import com.teamnk.kimiljung.data.repository.register.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val repository : RegisterRepository = RegisterRepository()
) : ViewModel() {
    private var success : MutableLiveData<Boolean> = MutableLiveData()
    private var failed : MutableLiveData<Boolean> = MutableLiveData()

    suspend fun verifyEmail(EmailVerificationRequest: RegisterEmailVerificationRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.verifyEmail(EmailVerificationRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: RegisterEmailVerificationCodeRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.checkEmailVerificationCode(emailVerificationCodeRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: RegisterUserIdDuplicationRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.checkUserIdDuplication(userIdDuplicationRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }
}