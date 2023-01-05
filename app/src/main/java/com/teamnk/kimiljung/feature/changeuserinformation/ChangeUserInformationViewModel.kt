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
    private val repository: ChangeUserInformationRepository,
    private val mApplication: Application,
) : AndroidViewModel(mApplication) {

    init {
        //TODO 자신의 정보 불러오는 로직 구현
    }

    private val _isSuccessful = MutableLiveData<Boolean>()
    internal val isSuccessful: LiveData<Boolean> = _isSuccessful

    private val _snackBarMessage = MutableLiveData<String>()
    internal val snackBarMessage: LiveData<String> = _snackBarMessage

    private lateinit var _email: String
    internal val email = _email

    private lateinit var _id: String
    internal val id = _id

    private lateinit var _profileImageUrl: String
    internal val profileImageUrl = _profileImageUrl

    //TODO 데이터는 ViewModel에서만 처리하기.. Activity나 Fragment 클래스에서 ~Request, ~Response의 존재 유무를 알면 안 됨!
    // 예를 들어서 로그인을 뷰모델에서 처리할 때 박준수 같은 경우에는 Activity에서 LoginRequest를 생성해서 LoginViewModel에서 LoginResponse를 Repository로
    // 전송해 주는 로직을 구상했었는데 이런 경우에는 LoginActivity가 필요 없는 LoginRequest를 알아버리기 때문에 단일책임원칙을 위배하므로 정말 별로이다
    // 해결책은 LoginActivity에서 Password랑 ID를 ViewModel로 전송하고 ViewModel에서 LoginRequest를 만드는 것이다

    internal fun changeUserInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.changeUserInformation(
                    ChangeUserInformationRequest(
                        email,
                        id,
                        profileImageUrl,
                    )
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _isSuccessful.postValue(
                        // TODO 아마 Interceptor 제대로 적용하면 이런 코드 필요 없을 것입니다
                        true,
                    )
                } else {
                    _isSuccessful.postValue(
                        false,
                    )
                    _snackBarMessage.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        ),
                    )
                }
            }.onFailure {
                _isSuccessful.postValue(
                    false,
                )
                _snackBarMessage.postValue(
                    mApplication.getString(
                        R.string.error_failed_to_connect_to_server,
                    ),
                )
            }
        }
    }
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
