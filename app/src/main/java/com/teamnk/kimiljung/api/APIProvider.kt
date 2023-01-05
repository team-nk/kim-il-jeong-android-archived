package com.teamnk.kimiljung.api

import com.teamnk.kimiljung.feature.changepassword.ChangePasswordAPI
import com.teamnk.kimiljung.feature.changeuserinformation.ChangeUserInformationAPI
import com.teamnk.kimiljung.feature.enterbirthday.EnterBirthdayAPI
import com.teamnk.kimiljung.feature.login.LoginAPI
import com.teamnk.kimiljung.feature.mypage.MyPageAPI
import com.teamnk.kimiljung.feature.post.PostAPI
import com.teamnk.kimiljung.feature.postcomment.CommentAPI
import com.teamnk.kimiljung.feature.register.RegisterAPI

val loginAPIProvider: LoginAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(LoginAPI::class.java)
}

val registerAPIProvider: RegisterAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(RegisterAPI::class.java)
}

val myPageAPIProvider: MyPageAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(MyPageAPI::class.java)
}

val changePasswordAPIProvider: ChangePasswordAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(ChangePasswordAPI::class.java)
}

val enterBirthdayAPIProvider: EnterBirthdayAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(EnterBirthdayAPI::class.java)
}

val postAPIProvider: PostAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(PostAPI::class.java)
}

val commentAPIProvider: CommentAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(CommentAPI::class.java)
}

val tokenAPIProvider: TokenAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(TokenAPI::class.java)
}

// TODO API를 이런 식으로 만들면 안되겠다는 것을 꺠달았다..
val changeUserInformationAPIProvider: ChangeUserInformationAPI by lazy {
    RetrofitClient.getRetrofit()!!.create(ChangeUserInformationAPI::class.java)
}