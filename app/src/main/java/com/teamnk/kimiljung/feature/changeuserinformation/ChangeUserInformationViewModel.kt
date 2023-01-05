package com.teamnk.kimiljung.feature.changeuserinformation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.api.changeUserInformationAPIProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ChangeUserInformationViewModel(
    repository: ChangeUserInformationRepository,
) : ViewModel() {

}

class ChangeUserInformationRepository {

    suspend fun changeUserInformation(changeUserInformationRequest: ChangeUserInformationRequest): Response<Void> {
        return changeUserInformationAPIProvider.changeUserInformation(
            changeUserInformationRequest,
        )
    }
}

data class ChangeUserInformationRequest(
    @SerializedName("email") val email: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("profile") val profileImageUrl: String,
)
