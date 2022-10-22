package com.teamnk.kimiljung.presentation.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationCodeRequest
import com.teamnk.kimiljung.data.model.register.RegisterEmailVerificationRequest
import com.teamnk.kimiljung.data.model.register.RegisterUserIdDuplicationRequest
import com.teamnk.kimiljung.data.repository.register.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {

    private var _success = MutableLiveData<Boolean>()
    private var _failure = MutableLiveData<Boolean>()

    val success: LiveData<Boolean>
        get() = _success
    val failure: LiveData<Boolean>
        get() = _failure

    suspend fun verifyEmail(EmailVerificationRequest: RegisterEmailVerificationRequest) {
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.verifyEmail(EmailVerificationRequest)
            }
        }.onSuccess {
            _success
        }.onFailure {
            _failure
        }
    }

    suspend fun checkEmailVerificationCode(emailVerificationCodeRequest: RegisterEmailVerificationCodeRequest) {
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.checkEmailVerificationCode(emailVerificationCodeRequest)
            }
        }.onSuccess {
            _success
        }.onFailure {
            _failure
        }
    }

    suspend fun checkUserIdDuplication(userIdDuplicationRequest: RegisterUserIdDuplicationRequest) {
        kotlin.runCatching {
            withContext(Dispatchers.Default) {
                repository.checkUserIdDuplication(userIdDuplicationRequest)
            }
        }.onSuccess {
            _success
        }.onFailure {
            _failure
        }
    }
}