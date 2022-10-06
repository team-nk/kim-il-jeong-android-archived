package com.teamnk.kimiljung.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamnk.kimiljung.dto.VerifyRequest
import com.teamnk.kimiljung.repository.auth.RegisterRepository

class RegisterViewModel(
    private val rp : RegisterRepository
) : ViewModel() {

    private val verifyResponse : MutableLiveData<Void> = MutableLiveData()

    suspend fun emailVerify(verifyRequest: VerifyRequest){
        verifyResponse.value = rp.verify(verifyRequest)
    }
}