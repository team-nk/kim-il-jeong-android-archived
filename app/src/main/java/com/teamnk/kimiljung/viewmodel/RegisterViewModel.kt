package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.dto.DuplicateRequest
import com.teamnk.kimiljung.dto.VerifyCodeRequest
import com.teamnk.kimiljung.dto.VerifyRequest
import com.teamnk.kimiljung.repository.auth.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val rp : RegisterRepository
) : ViewModel() {
    private var success : MutableLiveData<Boolean> = MutableLiveData()
    private var failed : MutableLiveData<Boolean> = MutableLiveData()

    suspend fun emailVerify(verifyRequest: VerifyRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.verify(verifyRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun verifyCode(verifyCodeRequest: VerifyCodeRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.verifyCode(verifyCodeRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

    suspend fun userIdDuplicate(duplicateRequest: DuplicateRequest){
        kotlin.runCatching {
            withContext(Dispatchers.Default){
                rp.checkIdDuplicated(duplicateRequest)
            }
        }.onSuccess {
            success
        }.onFailure {
            failed
        }
    }

}
